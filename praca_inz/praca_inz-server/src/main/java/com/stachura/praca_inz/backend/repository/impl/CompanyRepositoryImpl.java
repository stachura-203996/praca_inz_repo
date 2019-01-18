package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomCompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CompanyRepositoryImpl implements CustomCompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Company company) {
        entityManager.detach(company);
    }
}
