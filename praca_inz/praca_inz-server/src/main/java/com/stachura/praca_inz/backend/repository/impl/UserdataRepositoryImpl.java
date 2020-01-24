package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomUserdataRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class UserdataRepositoryImpl implements CustomUserdataRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Userdata entity) {
        entityManager.detach(entity);
    }
}
