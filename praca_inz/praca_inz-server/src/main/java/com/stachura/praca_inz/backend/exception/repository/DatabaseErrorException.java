package com.stachura.praca_inz.backend.exception.repository;

/**
 * Klasa wyjątku rzucanego przy naruszeniu ograniczeń nałożonych przez tabele w bazie danych
 */
public class DatabaseErrorException extends EntityException {

    public static final String DATABASE_ERROR = "error_database";
    public static final String ILLEGAL_ARGUMENT = "illegal_argument";
    public static final String USERNAME_TAKEN = "un_taken";
    public static final String EMAIL_TAKEN = "email_taken";
    public static final String ENTITY_EXISTS = "entity_exists";

    public DatabaseErrorException() {
    }

    public DatabaseErrorException(String message) {
        super(message);
    }

    public DatabaseErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link DatabaseErrorException}
     *
     * @param message wiadomość
     * @param cause przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static DatabaseErrorException createDbErrorException(String message, Throwable cause) {
        return new DatabaseErrorException(message, cause);
    }
}