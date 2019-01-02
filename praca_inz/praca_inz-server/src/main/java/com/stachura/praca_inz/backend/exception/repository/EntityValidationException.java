package com.stachura.praca_inz.backend.exception.repository;

/**
 * Klasa wyjątku rzucanego przy dodawaniu lub edycji obiektu którego walidacja przebiegła niepomyślnie
 */
public class EntityValidationException extends EntityException {

    public static final String USERDATA_DB_CONSTRAINT = "error_userdata_db_constraint";
    public static final String PERFORMANCE_REPORT_DB_CONSTRAINT = "error_performance_report_db_constraint";
    public static final String ACCOUNT_DB_CONSTRAINT = "error_account_db_constraint";
    public static final String BENEFIT_DB_CONSTRAINT = "error_benefit_db_constraint";
    public static final String POINT_DB_CONSTRAINT = "error_point_db_constraint";
    public static final String PURCHASE_DB_CONSTRAINT = "error_purchase_db_constraint";
    public static final String BENEFIT_PROPOSITION_DB_CONSTRAINT = "error_benefit_proposition_db_constraint";

    public EntityValidationException() {
    }

    public EntityValidationException(String message) {
        super(message);
    }

    public EntityValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Tworzy obiekt wyjątku {@link EntityValidationException}
     *
     * @param message wiadomość
     * @param cause   przycznyna, wyjątek nadrzędny
     * @return obiekt wyjątku
     */
    public static EntityValidationException createBeanWithValidation(String message, Throwable cause) {
        return new EntityValidationException(message, cause);
    }
}