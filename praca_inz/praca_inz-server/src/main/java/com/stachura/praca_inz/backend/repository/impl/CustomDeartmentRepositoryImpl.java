package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDepartmentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomDeartmentRepositoryImpl implements CustomDepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachDepartment(Department entity) {
     entityManager.detach(entity);
    }
}
