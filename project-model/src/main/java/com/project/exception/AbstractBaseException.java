package com.project.exception;

public abstract class AbstractBaseException extends RuntimeException {
    private static final long serialVersionUID = 7934168968831314440L;

    public abstract int getStatus();

    public AbstractBaseException(final String message) {
        super(message);
    }

    public AbstractBaseException(final String message, final Throwable cause) {
        super(message, cause);
    }
}