package com.project.service.task;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.project.model.slack.SlackResponse;
import com.project.service.SlackService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SlackEventConsumer implements Callable<SlackResponse> {
    private final SlackService service;
    private String event;

    @Autowired
    public SlackEventConsumer(final SlackService service) {
        this.service = service;
    }

    @Override
    public SlackResponse call() {
        return service.publish(getEvent());
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(final String event) {
        this.event = event;
    }
}