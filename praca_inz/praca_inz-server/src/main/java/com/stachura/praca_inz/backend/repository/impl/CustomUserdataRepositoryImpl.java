package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomUserdataRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomUserdataRepositoryImpl implements CustomUserdataRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachUserdata(Userdata entity) {
        entityManager.detach(entity);
    }
}
