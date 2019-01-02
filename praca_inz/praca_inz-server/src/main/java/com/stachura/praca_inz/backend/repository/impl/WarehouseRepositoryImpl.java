package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.exception.repository.*;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Repository
public class WarehouseRepositoryImpl extends AbstractRepository<Warehouse> implements WarehouseRepository {

    public WarehouseRepositoryImpl() {
        super(Warehouse.class);
    }

    @Override
    public void edit(Warehouse entity) throws EntityException {
        try {
            super.edit(entity);

        } catch (IllegalArgumentException iae) {
            throw EntityIllegalArgumentException.createIllegalArgument(EntityIllegalArgumentException.ILLEGAL_ARG, iae);
        } catch (OptimisticLockException ole) {
            throw EntityOptimisticLockException.createOptimisticLock(EntityOptimisticLockException.WAREHOUSE_OPTIMISTIC_LOCK, ole);
        } catch (PersistenceException pe) {
            throw DatabaseErrorException.createDbErrorException(DatabaseErrorException.DATABASE_ERROR, pe);
        } catch (ConstraintViolationException cve) {
            throw EntityValidationException.createBeanWithValidation(EntityValidationException.BENEFIT_DB_CONSTRAINT, cve);
        } catch (ValidationException ve) {
            throw EntityValidationException.createBeanWithValidation(EntityValidationException.BENEFIT_DB_CONSTRAINT, ve);
        }
    }

}
