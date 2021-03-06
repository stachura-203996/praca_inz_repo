package com.stachura.praca_inz.backend.exception;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;


public class ServiceException extends AppBaseException {

    public static final String INCORRECT_ID = "incorrect_id";
    public static final String INCORRECT_USERNAME = "incorrect_username";
    public static final String INCORRECT_HASH_ALGORITHM = "incorrect_hash_algorithm";
    public static final String SAME_PASSWORD = "same_password";
    public static final String INCORRECT_PASSWORD_RESET_DATA="incorect_password_reset_data";
    public static final String INCORRECT_PASSWORD = "incorrect_password";
    public static final String INCORRECT_LENGTH_PASSWORD = "incorrect_length_password";
    public static final String JSON_PARSE_ERROR = "json_parse_error";
    public static final String ENTITY_VALIDATION = "entity_validation_error";
    public static final String MAIL_ERROR = "send_mail_error";


    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link ServiceException} po nieudanym wczytaniu encji konta po id
     *
     * @param message wiadomość
     * @return obiekt wyjątku
     */
    public static ServiceException findAccountWithId(String message) {
        return new ServiceException(message);
    }

    /**
     * Tworzy obiekt wyjątku {@link ServiceException} po nieudanym wczytaniu encji konta po id
     *
     * @param message wiadomość
     * @param cause przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static ServiceException findAccountWithId(String message, Throwable cause) {
        return new ServiceException(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link ServiceException} po nieudanym wczytaniu encji konta po nazwie użytkownika
     *
     * @param message wiadomość
     * @param cause przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static ServiceException findAccountWithUsername(String message, Throwable cause) {
        return new ServiceException(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link ServiceException}
     *
     * @param message wiadomość
     * @param cause przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static ServiceException createServiceException(String message, Throwable cause) {
        return new ServiceException(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link ServiceException}
     *
     * @param message wiadomość
     * @return obiekt wyjątku
     */
    public static ServiceException createServiceException(String message) {
        return new ServiceException(message);
    }
}