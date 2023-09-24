package org.francescobasile.tisano.prove.exception;

public class ListException extends Throwable {

    public static final String standardMessage = "List exception.";
    public ListException() {
        super(standardMessage);
    }

    public ListException(String message) {
        super(message);
    }

    public ListException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListException(Throwable cause) {
        super(cause);
    }

    protected ListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
