package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDeviceModelRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomDeviceModelRepositoryImpl implements CustomDeviceModelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachDeviceModel(DeviceModel entity) {
        entityManager.detach(entity);
    }
}
