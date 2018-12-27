package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Shipment;
import com.stachura.praca_inz.backend.web.dto.ShipmentListElementDto;

import java.util.List;

public interface ShipmentService {
    Shipment getShipmentById(Long id);

    List<ShipmentListElementDto> getAllShipments();

    void createNewShipment(Shipment shipment);

    Shipment updateShipment(Shipment shipment);

    void deleteShipmentById(Long id);

    void deleteShipment(Shipment shipment);
}
