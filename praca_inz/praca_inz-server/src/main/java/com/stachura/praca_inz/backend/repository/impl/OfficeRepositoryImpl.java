package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.Office_;
import com.stachura.praca_inz.backend.repository.interfaces.OfficeRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class OfficeRepositoryImpl implements OfficeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Office find(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> query = builder.createQuery(Office.class);

        Root<Office> root = query.from(Office.class);
//        root.fetch(Office_.devices, JoinType.LEFT);
//        Fetch<Office, Office> departmentFetch = root.fetch(Office_.offices, JoinType.LEFT);
//        Fetch<Office, Userdata> employeeFetch = departmentFetch.fetch(Office_.employees, JoinType.LEFT);
//        employeeFetch.fetch(Employee_.address, JoinType.LEFT);
//        departmentFetch.fetch(Office_.offices, JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Office_.id), id);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
    }

    @Override
    public Office find(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> query = builder.createQuery(Office.class);

        Root<Office> root = query.from(Office.class);
        // root.fetch(Office_.devices, JoinType.LEFT);
//        Fetch<Office, Office> officeFetchFetch = root.fetch(Office_.offices, JoinType.LEFT);
//        Fetch<Office, Userdata> employeeFetch = departmentFetch.fetch(Office_.employees, JoinType.LEFT);
//        employeeFetch.fetch(Employee_.address, JoinType.LEFT);
//        officeFetchFetch.fetch(Office_., JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Office_.name), name);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
    }

    @Override
    public List<Office> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> query = builder.createQuery(Office.class);
        Root<Office> root = query.from(Office.class);
        query.select(root).distinct(true);
        TypedQuery<Office> allQuery = entityManager.createQuery(query);

        return allQuery.getResultList();
    }

    @Override
    public void create(Office department) {
        entityManager.persist(department);
    }

    @Override
    public Office update(Office department) {
        return entityManager.merge(department);
    }

    @Override
    public void delete(Long id) {
        Office department = entityManager.find(Office.class, id);
        delete(department);
    }

    @Override
    public void delete(Office department) {
        entityManager.remove(department);
    }
}
