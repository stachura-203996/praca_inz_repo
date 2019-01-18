package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDeviceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DeviceRepositoryImpl implements CustomDeviceRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Device entity) {
        entityManager.detach(entity);
    }
}
