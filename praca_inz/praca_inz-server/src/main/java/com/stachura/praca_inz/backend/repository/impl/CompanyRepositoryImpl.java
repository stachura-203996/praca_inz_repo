package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.CompanyRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;

@Repository
public class CompanyRepositoryImpl extends AbstractRepository<Company> implements CompanyRepository{


    public CompanyRepositoryImpl() {
        super(Company.class);
    }

    @Override
    public Company find(Long id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);

        Root<Company> root = query.from(Company.class);
        Fetch<Company, Department> departmentFetch = root.fetch(Company_.departments, JoinType.LEFT);
        departmentFetch.fetch(Department_.offices, JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Company_.id), id);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
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

}
