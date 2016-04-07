package com.project.exception;

public class SlackApiException extends AbstractBaseException {
    private static final long serialVersionUID = 1567846893330865463L;

    public SlackApiException(final String message) {
        super(message);
    }

    public SlackApiException(final String message, final Throwable t) {
        super(message, t);
    }

    @Override
    public int getStatus() {
        return 404;
    }
}