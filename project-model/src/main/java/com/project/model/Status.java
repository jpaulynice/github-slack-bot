package com.project.model;

public class Status {
    private boolean running;

    public Status() {
    }

    public Status(final boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }
}