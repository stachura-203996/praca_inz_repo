package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomOfficeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomOfficeRepositoryImpl implements CustomOfficeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachOffice(Office entity) {
        entityManager.detach(entity);
    }
}
