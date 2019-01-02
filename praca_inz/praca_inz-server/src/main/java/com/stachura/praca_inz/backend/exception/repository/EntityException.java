package com.stachura.praca_inz.backend.exception.repository;

import com.stachura.praca_inz.backend.exception.service.ServiceException;


/**
 * Klasa reprezentująca bazowy wyjątek związany z obiektem encji
 */
public class EntityException extends ServiceException {

    public EntityException() {
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }
}