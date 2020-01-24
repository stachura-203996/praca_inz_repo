package com.stachura.praca_inz.backend.exception;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Klasa wyjątku rzucanego przy wyszukiwaniu obiektu, który nie istnieje w bazie danych, o podanym parametrze
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotInDatabaseException extends SystemBaseException {

    public static final String NO_OBJECT = "error_no_object_in_database";
    public static final String NO_USERNAME="error_no_username_in_database";

    public EntityNotInDatabaseException() {
    }

    public EntityNotInDatabaseException(String message) {
        super(message);
    }

    public EntityNotInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}