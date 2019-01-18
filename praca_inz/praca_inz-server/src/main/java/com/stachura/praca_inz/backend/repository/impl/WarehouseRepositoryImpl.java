package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomWarehouseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class WarehouseRepositoryImpl implements CustomWarehouseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Warehouse entity) {
        entityManager.detach(entity);
    }
}
