package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.exception.repository.*;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Company_;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Department_;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.CompanyRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Repository
public class CompanyRepositoryImpl extends AbstractRepository<Company> implements CompanyRepository {


    public CompanyRepositoryImpl() {
        super(Company.class);
    }


    @Override
    public Company find(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);

        Root<Company> root = query.from(Company.class);
        Fetch<Company, Department> departmentFetch = root.fetch(Company_.departments, JoinType.LEFT);
        departmentFetch.fetch(Department_.offices, JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Company_.name), name);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
    }


    @Override
    public void edit(Company entity) throws EntityException {
        try {
            super.edit(entity);
        } catch (IllegalArgumentException iae) {
            throw EntityIllegalArgumentException.createIllegalArgument(EntityIllegalArgumentException.ILLEGAL_ARG, iae);
        } catch (OptimisticLockException ole) {
            throw EntityOptimisticLockException.createOptimisticLock(EntityOptimisticLockException.COMPANY_OPTIMISTIC_LOCK, ole);
        } catch (PersistenceException pe) {
            throw DatabaseErrorException.createDbErrorException(DatabaseErrorException.DATABASE_ERROR, pe);
        } catch (ConstraintViolationException cve) {
            throw EntityValidationException.createBeanWithValidation(EntityValidationException.BENEFIT_DB_CONSTRAINT, cve);
        } catch (ValidationException ve) {
            throw EntityValidationException.createBeanWithValidation(EntityValidationException.BENEFIT_DB_CONSTRAINT, ve);
        }
    }
}
