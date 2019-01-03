package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Shipment;
import com.stachura.praca_inz.backend.web.dto.ShipmentListElementDto;

import java.util.List;

public interface ShipmentService {
    Shipment getShipmentById(Long id);

    List<ShipmentListElementDto> getAllShipments();

    List<ShipmentListElementDto> getAllShipmentsForWarehouseman(Long id);

    void createNewShipment(Shipment shipment) throws ServiceException;

    void updateShipment(Shipment shipment) throws ServiceException;

    void deleteShipmentById(Long id);

    void deleteShipment(Shipment shipment);
}
