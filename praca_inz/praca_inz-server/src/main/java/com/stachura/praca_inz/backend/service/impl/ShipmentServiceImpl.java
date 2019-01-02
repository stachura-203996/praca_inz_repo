package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Shipment;
import com.stachura.praca_inz.backend.repository.interfaces.ShipmentRepository;
import com.stachura.praca_inz.backend.service.ShipmentService;
import com.stachura.praca_inz.backend.web.dto.ShipmentListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.ShipmentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SHIPMENT_READ')")
    public Shipment getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.find(id);
        if (shipment.isDeleted()) {
            return null;
        }
        return shipment;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SHIPMENT_LIST_READ')")
    public List<ShipmentListElementDto> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        List<ShipmentListElementDto> shipmentListElementDtos = new ArrayList<>();
        for (Shipment a : shipments) {
            if (!a.isDeleted()) {
                shipmentListElementDtos.add(ShipmentConverter.toShipmentListElement(a));
            }
        }
        return shipmentListElementDtos;
    }

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SHIPMENT_CREATE')")
    public void createNewShipment(Shipment shipment) throws ServiceException{
        try {
            shipmentRepository.create(shipment);
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SHIPMENT_UPDATE')")
    public void updateShipment(Shipment shipment) throws ServiceException {
        shipmentRepository.update(shipment);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SHIPMENT_DELETE')")
    public void deleteShipmentById(Long id) {
        shipmentRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SHIPMENT_DELETE')")
    public void deleteShipment(Shipment shipment) {
        shipmentRepository.find(shipment.getId()).setDeleted(true);
    }
}
