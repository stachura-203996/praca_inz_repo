package com.stachura.praca_inz.backend.exception;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;

/**
 * Klasa wyjątku rzucanego przy naruszeniu blokady optymistycznej przy edycji obiektu
 */

public class EntityOptimisticLockException extends SystemBaseException {

    public static final String OPTIMISTIC_LOCK = "error_optimistic_lock";

    public EntityOptimisticLockException() {
    }

    public EntityOptimisticLockException(String message) {
        super(message);
    }

    public EntityOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

}