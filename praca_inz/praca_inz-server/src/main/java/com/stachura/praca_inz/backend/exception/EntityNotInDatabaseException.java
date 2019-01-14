package com.stachura.praca_inz.backend.exception;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;

/**
 * Klasa wyjątku rzucanego przy wyszukiwaniu obiektu, który nie istnieje w bazie danych, o podanym parametrze
 */
public class EntityNotInDatabaseException extends AppBaseException {

    public static final String NO_OBJECT = "error_no_object_in_database";

    public EntityNotInDatabaseException() {
    }

    public EntityNotInDatabaseException(String message) {
        super(message);
    }

    public EntityNotInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link EntityNotInDatabaseException}
     *
     * @param message wiadomość
     * @param cause przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static EntityNotInDatabaseException createObjectWithNoResult(String message, Throwable cause) {
        return new EntityNotInDatabaseException(message, cause);
    }
}