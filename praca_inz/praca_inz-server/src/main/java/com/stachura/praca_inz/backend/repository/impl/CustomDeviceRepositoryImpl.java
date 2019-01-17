package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDeviceRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomDeviceRepositoryImpl implements CustomDeviceRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachDevice(Device entity) {
        entityManager.detach(entity);
    }
}
