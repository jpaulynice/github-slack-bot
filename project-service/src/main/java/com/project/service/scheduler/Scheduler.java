package com.project.service.scheduler;

import com.project.model.Status;

public interface Scheduler {
    void start(long delay, long frequency);

    void stop();

    Status status();
}