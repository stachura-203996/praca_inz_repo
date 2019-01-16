package com.stachura.praca_inz.backend.exception.base;

/**
 * Klasa reprezentująca bazowy wyjątek aplikacyjny
 */

public class AppBaseException extends Exception {

    protected AppBaseException() {
    }

    protected AppBaseException(String message) {
        super(message);
    }

    protected AppBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}