package org.lzsf.exception;

public class NotFoundMessageServiceException extends RuntimeException{
    public NotFoundMessageServiceException() {
        super();
    }

    public NotFoundMessageServiceException(String message) {
        super(message);
    }

    public NotFoundMessageServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMessageServiceException(Throwable cause) {
        super(cause);
    }

    protected NotFoundMessageServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
