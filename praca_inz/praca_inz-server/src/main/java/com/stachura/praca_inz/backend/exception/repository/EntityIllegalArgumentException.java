package com.stachura.praca_inz.backend.exception.repository;

/**
 * Klasa wyjątku rzucanego przy przekazaniu niewłąściwych parametrów do metody repozytorium
 */
public class EntityIllegalArgumentException extends EntityException {

    public static final String ILLEGAL_ARG = "error_illegal_argument";

    public EntityIllegalArgumentException() {
    }

    public EntityIllegalArgumentException(String message) {
        super(message);
    }

    public EntityIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link EntityIllegalArgumentException}
     *
     * @param message wiadomość
     * @param cause przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static EntityIllegalArgumentException createIllegalArgument(String message, Throwable cause) {
        return new EntityIllegalArgumentException(message, cause);
    }
}
