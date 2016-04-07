package com.project.model;

public class StartRequest {
    private long delay;
    private long frequency;

    public StartRequest() {
    }

    public StartRequest(final long delay, final long frequency) {
        this.delay = delay;
        this.frequency = frequency;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(final long delay) {
        this.delay = delay;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(final long frequency) {
        this.frequency = frequency;
    }
}