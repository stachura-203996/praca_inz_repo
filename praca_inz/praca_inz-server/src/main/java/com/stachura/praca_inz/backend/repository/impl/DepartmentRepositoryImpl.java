package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Department_;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.Office_;
import com.stachura.praca_inz.backend.repository.DepartmentRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department find(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);

        Root<Department> root = query.from(Department.class);
//        root.fetch(Department_.devices, JoinType.LEFT);
        Fetch<Department, Office> departmentFetch = root.fetch(Department_.offices, JoinType.LEFT);
//        Fetch<Department, Userdata> employeeFetch = departmentFetch.fetch(Department_.employees, JoinType.LEFT);
//        employeeFetch.fetch(Employee_.address, JoinType.LEFT);
//        departmentFetch.fetch(Department_.offices, JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Department_.id), id);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
    }

    @Override
    public Department find(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);

        Root<Department> root = query.from(Department.class);
        // root.fetch(Department_.devices, JoinType.LEFT);
        Fetch<Department, Office> officeFetchFetch = root.fetch(Department_.offices, JoinType.LEFT);
//        Fetch<Department, Userdata> employeeFetch = departmentFetch.fetch(Department_.employees, JoinType.LEFT);
//        employeeFetch.fetch(Employee_.address, JoinType.LEFT);
//        officeFetchFetch.fetch(Office_., JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Department_.name), name);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
    }

    @Override
    public List<Department> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        query.select(root).distinct(true);
        TypedQuery<Department> allQuery = entityManager.createQuery(query);

        return allQuery.getResultList();
    }

    @Override
    public void create(Department department) {
        entityManager.persist(department);
    }

    @Override
    public Department update(Department department) {
        return entityManager.merge(department);
    }

    @Override
    public void delete(Long id) {
        Department department = entityManager.find(Department.class, id);
        delete(department);
    }

    @Override
    public void delete(Department department) {
        entityManager.remove(department);
    }
}
