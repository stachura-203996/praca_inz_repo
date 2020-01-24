package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDepartmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DepartmentRepositoryImpl implements CustomDepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Department department) {
     entityManager.detach(department);
    }
}
