package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomCompanyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomCompanyRepositoryImpl implements CustomCompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachCompany(Company entity) {
        entityManager.detach(entity);
    }
}
