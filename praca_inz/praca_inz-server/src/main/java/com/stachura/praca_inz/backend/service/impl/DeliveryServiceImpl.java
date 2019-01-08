package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.repository.interfaces.DeliveryRepository;
import com.stachura.praca_inz.backend.service.DeliveryService;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeliveryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_READ')")
    public Delivery getDeliveryById(Long id) {
        Delivery delivery = deliveryRepository.find(id);
        if (delivery.isDeleted()) {
            return null;
        }
        return delivery;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_LIST_READ')")
    public List<DeliveryListElementDto> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        List<DeliveryListElementDto> companiesDto = new ArrayList<>();
        for (Delivery a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_LIST_READ')")
    public List<DeliveryListElementDto> getAllDeliveriesForWarehouseman(Long id) {
        List<Delivery> deliveries = deliveryRepository.findAll().stream().filter(x->x.getSenderWarehouse().getUser().getId().equals(id)).collect(Collectors.toList());
        List<DeliveryListElementDto> companiesDto = new ArrayList<>();
        for (Delivery a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_CREATE')")
    public void createNewDelivery(Delivery delivery) throws ServiceException {
        try {
            deliveryRepository.create(delivery);

        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_UPDATE')")
    public void updateDelivery(Delivery delivery)throws ServiceException {
        deliveryRepository.update(delivery);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_DELETE')")
    public void deleteDeliveryById(Long id) {
        deliveryRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_DELETE')")
    public void deleteDelivery(Delivery delivery) {
        deliveryRepository.find(delivery.getId()).setDeleted(true);
    }


}
