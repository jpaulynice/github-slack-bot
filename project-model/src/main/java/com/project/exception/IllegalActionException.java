package com.project.exception;

public class IllegalActionException extends AbstractBaseException {
    private static final long serialVersionUID = 1567846893330865463L;

    public IllegalActionException(final String message) {
        super(message);
    }

    public IllegalActionException(final String message, final Throwable t) {
        super(message, t);
    }

    @Override
    public int getStatus() {
        return 400;
    }
}