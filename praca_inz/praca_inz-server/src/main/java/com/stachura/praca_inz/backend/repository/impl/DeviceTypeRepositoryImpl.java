package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.repository.EntityValidationException;
import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceTypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@Repository
public class DeviceTypeRepositoryImpl extends AbstractRepository<DeviceType> implements DeviceTypeRepository {

    protected DeviceTypeRepositoryImpl() {
        super(DeviceType.class);}

    @Override
    public void create(DeviceType entity) throws EntityException {
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
