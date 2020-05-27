package ru.taranov.homeSale.exception;

public class AdvertNotFoundException extends RuntimeException {

    public AdvertNotFoundException() {
    }

    public AdvertNotFoundException(String message) {
        super(message);
    }

    public AdvertNotFoundException(Throwable cause) {
        super(cause);
    }

    public AdvertNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdvertNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
