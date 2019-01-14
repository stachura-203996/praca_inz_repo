package com.stachura.praca_inz.backend.exception.base;


import org.springframework.transaction.annotation.Transactional;

/**
 * Klasa reprezentująca bazowy wyjątek aplikacyjny
 */

@Transactional/*(rollbackFor= true)*/
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