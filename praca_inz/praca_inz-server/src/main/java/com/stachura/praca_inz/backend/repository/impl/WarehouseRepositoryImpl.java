package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.exception.repository.*;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.Warehouse_;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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


    @Override
    public Warehouse findUserWarehouse(Long userId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Warehouse> query = builder.createQuery(Warehouse.class);

        Root<Warehouse> root = query.from(Warehouse.class);
        query.select(root).distinct(true);

        Predicate userIdPredicate = builder.equal(root.get(Warehouse_.USER), userId);
        Predicate typePredicate=builder.equal(root.get(Warehouse_.WAREHOUSE_TYPE),WarehouseType.USER);
        query.where(builder.and(userIdPredicate));
        query.where(builder.and(typePredicate));

        return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
    }

    @Override
    public Warehouse findOfficeWarehouse(Long userId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Warehouse> query = builder.createQuery(Warehouse.class);

        Root<Warehouse> root = query.from(Warehouse.class);
        query.select(root).distinct(true);

        Predicate userIdPredicate = builder.equal(root.get(Warehouse_.USER), userId);
        Predicate typePredicate=builder.equal(root.get(Warehouse_.WAREHOUSE_TYPE),WarehouseType.OFFICE);
        query.where(builder.and(userIdPredicate));
        query.where(builder.and(typePredicate));

        return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
    }


}
