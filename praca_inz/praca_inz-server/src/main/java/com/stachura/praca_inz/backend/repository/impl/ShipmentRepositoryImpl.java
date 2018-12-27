package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Shipment;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.ShipmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ShipmentRepositoryImpl extends AbstractRepository<Shipment> implements ShipmentRepository {

    protected ShipmentRepositoryImpl() {
        super(Shipment.class);
    }
}