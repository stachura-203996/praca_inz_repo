package com.stachura.praca_inz.backend.exception;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Klasa wyjątku rzucanego przy naruszeniu ograniczeń nałożonych przez tabele w bazie danych
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DatabaseErrorException extends AppBaseException {

    public static final String DATABASE_ERROR = "error_database";
    public static final String ILLEGAL_ARGUMENT = "illegal_argument";
    // User response codes
    public static final String EMAIL_TAKEN = "email_taken";
    public static final String USERNAME_TAKEN = "un_taken";
    public static final String SUCCESS = "success";
    public static final String SAME_PASSWORD="same_password";

    //Company response codes
    public static final String COMPANY_NAME_TAKEN= "cn_taken";

    //Department response codes
    public static final String DEPARTMENT_NAME_TAKEN= "dn_taken";

    //Office response codes
    public static final String OFFICE_NAME_TAKEN= "on_taken";

    //Warehouse response codes
    public static final String WAREHOUSE_NAME_TAKEN= "wn_taken";

    //Device response codes
    public static final String SERIAL_NUMBER_NAME_TAKEN= "sn_taken";

    //Device mode response codes
    public static final String DEVICE_MODEL_NAME_NAME_TAKEN= "dmn_taken";


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