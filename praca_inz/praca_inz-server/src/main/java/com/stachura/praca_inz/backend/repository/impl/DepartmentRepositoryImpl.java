package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Department_;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

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



  
}
