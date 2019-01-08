package com.stachura.praca_inz.backend.exception.repository;

/**
 * Klasa wyjątku rzucanego przy naruszeniu blokady optymistycznej przy edycji obiektu
 */
public class EntityOptimisticLockException extends EntityException {

    public static final String USERDATA_OPTIMISTIC_LOCK = "error_userdata_optimistic_lock";
    public static final String PERFORMANCE_REPORT_OPTIMISTIC_LOCK = "error_performance_report_optimistic_lock";
    public static final String COMPANY_OPTIMISTIC_LOCK = "error_company_optimistic_lock";
    public static final String OFFICE_OPTIMISTIC_LOCK = "error_office_optimistic_lock";
    public static final String DEPARTMENT_OPTIMISTIC_LOCK = "error_department_optimistic_lock";
    public static final String DEVICE_OPTIMISTIC_LOCK = "error_device_optimistic_lock";
    public static final String DELIVERY_OPTIMISTIC_LOCK = "error_delivery_optimistic_lock";
    public static final String DEVICE_MODEL_OPTIMISTIC_LOCK = "error_device_model_optimistic_lock";
    public static final String REPORT_OPTIMISTIC_LOCK = "error_device_model_optimistic_lock";
    public static final String REQUEST_OPTIMISTIC_LOCK = "error_device_model_optimistic_lock";
    public static final String SYSTEM_MESSAGE_OPTIMISTIC_LOCK = "error_device_model_optimistic_lock";
    public static final String WAREHOUSE_OPTIMISTIC_LOCK = "error_device_model_optimistic_lock";
    public static final String ACCOUNT_OPTIMISTIC_LOCK = "error_account_optimistic_lock";
    public static final String POINT_OPTIMISTIC_LOCK = "error_point_optimistic_lock";

    public EntityOptimisticLockException() {
    }

    private EntityOptimisticLockException(String message) {
        super(message);
    }

    private EntityOptimisticLockException(String message, Throwable cause) {
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