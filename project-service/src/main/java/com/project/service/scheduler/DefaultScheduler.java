package com.project.service.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.exception.IllegalActionException;
import com.project.model.Status;
import com.project.service.EmailService;
import com.project.service.task.GithubEventFetcher;

@Component
public class DefaultScheduler implements Scheduler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ObjectFactory<GithubEventFetcher> prototypeFactory;
    private final EmailService emailService;

    private ScheduledFuture<?> future;
    private boolean running = false;

    private static ScheduledExecutorService producerExecutor;

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int KEE_ALIVE = 30;
    private static final int THREADS = 40;

    @Autowired
    public DefaultScheduler(final EmailService emailService,
            final ObjectFactory<GithubEventFetcher> prototypeFactory) {
        this.emailService = emailService;
        this.prototypeFactory = prototypeFactory;
    }

    @Override
    public void start(final long delay, final long frequency) {
        if (running) {
            throw new IllegalActionException("the task is already running.");
        }
        producerExecutor = Executors.newSingleThreadScheduledExecutor();
        future = producerExecutor.scheduleAtFixedRate(producer(), delay, frequency, TimeUnit.MINUTES);
        log.debug("task scheduled to run every {} minutes and an initial delay {} minutes", frequency, delay);
        running = true;
    }

    private Runnable producer() {
        final RejectedExecutionHandler rejHandler = (final Runnable r, final ThreadPoolExecutor e) -> {
            emailService.send("slack event");
        };

        final BlockingQueue<Runnable> workers = new LinkedBlockingQueue<>(THREADS);

        final ExecutorService executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEE_ALIVE, TimeUnit.SECONDS, workers, rejHandler);
        final GithubEventFetcher producer = prototypeFactory.getObject();
        producer.setConsumerExecutor(executor);

        return producer;
    }

    @Override
    public void stop() {
        if (!running) {
            throw new IllegalActionException("the task is not running.");
        }
        if (future != null) {
            future.cancel(true);
        }
        log.debug("successfully cancelled running task...");

        producerExecutor.shutdown();
        try {
            producerExecutor.awaitTermination(15, TimeUnit.SECONDS);
            log.debug("successfully shut down scheduler...");
            running = false;
        } catch (final InterruptedException e) {
            log.error("Exception while shutting down executor", e);
        }
    }

    @Override
    public Status status() {
        return new Status(running);
    }
}
