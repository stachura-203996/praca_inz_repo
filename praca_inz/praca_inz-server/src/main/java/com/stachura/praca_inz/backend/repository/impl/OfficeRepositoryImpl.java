package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomOfficeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OfficeRepositoryImpl implements CustomOfficeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Office entity) {
        entityManager.detach(entity);
    }
}
