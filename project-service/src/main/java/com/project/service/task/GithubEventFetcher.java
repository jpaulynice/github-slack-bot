package com.project.service.task;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.project.model.github.GithubEvent;
import com.project.model.slack.SlackResponse;
import com.project.service.GithubService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GithubEventFetcher implements Runnable {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final Gson GSON = new Gson();
    private final GithubService service;
    private ExecutorService consumerExecutor;
    private CompletionService<SlackResponse> completionService;
    private final ObjectFactory<SlackEventConsumer> prototypeFactory;

    @Autowired
    public GithubEventFetcher(final GithubService service,
            final ObjectFactory<SlackEventConsumer> prototypeFactory) {
        this.service = service;
        this.prototypeFactory = prototypeFactory;
    }

    @Override
    public void run() {
        log.debug("Fetching github events...");
        final List<GithubEvent> list = service.getPublicEvents();
        if (!list.isEmpty()) {
            submit(list);
            getResults(list);
        } else {
            log.debug("No recent events found");
        }
    }

    private void submit(final List<GithubEvent> list) {
        completionService = new ExecutorCompletionService<SlackResponse>(
                getConsumerExecutor());

        for (final GithubEvent event : list) {
            final SlackEventConsumer consumer = prototypeFactory.getObject();
            consumer.setEvent(GSON.toJson(event, GithubEvent.class));

            completionService.submit(consumer);

            log.debug("submitted event with id: {} for processing", event
                    .getId());
        }
    }

    private void getResults(final List<GithubEvent> list) {
        for (int i = 0; i < list.size(); i++) {
            try {
                final Future<SlackResponse> future = completionService.take();
                final SlackResponse r = future.get();
                log.debug("Slack API response: {}", r);
            } catch (final InterruptedException e) {
                log.error("the thread was interrupted", e);
            } catch (final ExecutionException e) {
                log.error("execution exception.", e);
            }
        }
    }

    public ExecutorService getConsumerExecutor() {
        return consumerExecutor;
    }

    public void setConsumerExecutor(final ExecutorService consumerExecutor) {
        this.consumerExecutor = consumerExecutor;
    }
}