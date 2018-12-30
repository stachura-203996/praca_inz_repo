package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Shipment;

import java.util.List;

public interface ShipmentRepository {

    Shipment find(Long id);

    List<Shipment> findAll();

    void create(Shipment shipment)throws EntityException;

    Shipment update(Shipment shipment)throws EntityException;

    void remove(Long id);

    void remove(Shipment shipment);
}
