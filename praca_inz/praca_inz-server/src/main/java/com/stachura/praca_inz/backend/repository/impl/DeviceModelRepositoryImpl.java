package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDeviceModelRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DeviceModelRepositoryImpl implements CustomDeviceModelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(DeviceModel entity) {
        entityManager.detach(entity);
    }
}
