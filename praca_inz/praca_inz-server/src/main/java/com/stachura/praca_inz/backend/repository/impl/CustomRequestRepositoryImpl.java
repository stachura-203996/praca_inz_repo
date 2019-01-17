package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomRequestRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomRequestRepositoryImpl implements CustomRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachRequest(Request entity) {
        entityManager.detach(entity);
    }
}
