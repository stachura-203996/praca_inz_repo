package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomRequestRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class RequestRepositoryImpl implements CustomRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Request entity) {
        entityManager.detach(entity);
    }
}
