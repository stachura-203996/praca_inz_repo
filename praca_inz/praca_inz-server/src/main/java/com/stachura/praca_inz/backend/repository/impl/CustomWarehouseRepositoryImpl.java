package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomWarehouseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Transactional
public class CustomWarehouseRepositoryImpl implements CustomWarehouseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachWarehouse(Warehouse entity) {
        entityManager.detach(entity);
    }
}
