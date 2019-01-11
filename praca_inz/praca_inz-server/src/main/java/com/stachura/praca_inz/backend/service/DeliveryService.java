package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;

import java.util.List;

public interface DeliveryService {

    Delivery getDeliveryById(Long id) throws ServiceException;

    List<DeliveryListElementDto> getAllDeliveries(String username) throws ServiceException;

    List<DeliveryListElementDto> getAllDeliveriesForWarehouseman(String username) throws ServiceException;

    void createNewDelivery(Delivery delivery) throws ServiceException;

    void updateDelivery(Delivery delivery) throws ServiceException;

    void deleteDeliveryById(Long id) throws ServiceException;

}
