package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.exception.repository.*;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Department_;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Repository
public class DepartmentRepositoryImpl extends AbstractRepository<Department> implements DepartmentRepository {

    public DepartmentRepositoryImpl() {
        super(Department.class);
    }



    @Override
    public Department find(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);

        Root<Department> root = query.from(Department.class);
        Fetch<Department, Office> officeFetchFetch = root.fetch(Department_.offices, JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Department_.name), name);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
    }

    @Override
    public void edit(Department entity) throws EntityException {
        try {
            super.edit(entity);

        } catch (IllegalArgumentException iae) {
            throw EntityIllegalArgumentException.createIllegalArgument(EntityIllegalArgumentException.ILLEGAL_ARG, iae);
        } catch (OptimisticLockException ole) {
            throw EntityOptimisticLockException.createOptimisticLock(EntityOptimisticLockException.DELIVERY_OPTIMISTIC_LOCK, ole);
        } catch (PersistenceException pe) {
            throw DatabaseErrorException.createDbErrorException(DatabaseErrorException.DATABASE_ERROR, pe);
        } catch (ConstraintViolationException cve) {
            throw EntityValidationException.createBeanWithValidation(EntityValidationException.BENEFIT_DB_CONSTRAINT, cve);
        } catch (ValidationException ve) {
            throw EntityValidationException.createBeanWithValidation(EntityValidationException.BENEFIT_DB_CONSTRAINT, ve);
        }
    }
  
}
