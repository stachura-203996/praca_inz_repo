package com.stachura.praca_inz.backend.exception;


import org.springframework.transaction.annotation.Transactional;

/**
 * Klasa reprezentująca bazowy wyjątek aplikacyjny
 */
//TODO tu jakaś adnotacja ma byc ale jeszcze nie wiem ApplicationException
//@Transactional(rollback = true)
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