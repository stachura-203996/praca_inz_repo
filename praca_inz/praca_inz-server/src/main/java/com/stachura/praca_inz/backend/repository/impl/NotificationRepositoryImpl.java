package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.repository.EntityValidationException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.NotificationRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@Repository
public class NotificationRepositoryImpl extends AbstractRepository<Notification> implements NotificationRepository {

    public NotificationRepositoryImpl() {super(Notification.class);}

    @Override
    public void create(Notification entity) throws EntityException {
        try {
            super.create(entity);
        } catch (PersistenceException pe) {
            throw DatabaseErrorException.createDbErrorException(DatabaseErrorException.DATABASE_ERROR, pe);
        } catch (IllegalArgumentException iae) {
            throw DatabaseErrorException.createDbErrorException(DatabaseErrorException.ILLEGAL_ARGUMENT, iae);
        } catch (ConstraintViolationException cve) {
            throw EntityValidationException.createBeanWithValidation(EntityValidationException.BENEFIT_DB_CONSTRAINT, cve);
        }
    }

}
