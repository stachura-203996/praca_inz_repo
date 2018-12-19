package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseRepositoryImpl extends AbstractRepository<Warehouse> implements WarehouseRepository {

    public WarehouseRepositoryImpl() {
        super(Warehouse.class);
    }


}
