package com.stachura.praca_inz.backend.exception.base;

/**
 * Klasa reprezentująca bazowy wyjątek aplikacyjny
 */

public class SystemBaseException extends Exception {

    protected SystemBaseException() {
    }

    protected SystemBaseException(String message) {
        super(message);
    }

    protected SystemBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}