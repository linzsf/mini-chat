package org.lzsf.exception;

public class UnknownMessageType extends RuntimeException{
    private static final long serialVersionUID = -6849054299660462870L;

    public UnknownMessageType() {
        super();
    }

    public UnknownMessageType(String message) {
        super(message);
    }

    public UnknownMessageType(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownMessageType(Throwable cause) {
        super(cause);
    }

    protected UnknownMessageType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
