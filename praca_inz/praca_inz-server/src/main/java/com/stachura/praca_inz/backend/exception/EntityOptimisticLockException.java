package com.stachura.praca_inz.backend.exception;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;

/**
 * Klasa wyjątku rzucanego przy naruszeniu blokady optymistycznej przy edycji obiektu
 */
public class EntityOptimisticLockException extends AppBaseException {

    public static final String OPTIMISTIC_LOCK = "error_optimistic_lock";

    public EntityOptimisticLockException() {
    }

    public EntityOptimisticLockException(String message) {
        super(message);
    }

    public EntityOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link EntityOptimisticLockException}
     *
     * @param message wiadomość
     * @return obiekt wyjątku
     */
    public static EntityOptimisticLockException createOptimisticLock(String message) {
        return new EntityOptimisticLockException(message);
    }

    /**
     * Tworzy obiekt wyjątku {@link EntityOptimisticLockException}
     *
     * @param message wiadomość
     * @param cause przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static EntityOptimisticLockException createOptimisticLock(String message, Throwable cause) {
        return new EntityOptimisticLockException(message, cause);
    }
}