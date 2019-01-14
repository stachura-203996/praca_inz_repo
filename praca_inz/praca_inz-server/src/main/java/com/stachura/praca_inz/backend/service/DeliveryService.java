package com.stachura.praca_inz.backend.service;


import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;

import java.util.List;

public interface DeliveryService {

    Delivery getDeliveryById(Long id) throws AppBaseException, AppBaseException;

    List<DeliveryListElementDto> getAllDeliveries(String username) throws AppBaseException, AppBaseException;

    List<DeliveryListElementDto> getAllDeliveriesForWarehouseman(String username) throws AppBaseException, AppBaseException;

    void createNewDelivery(Delivery delivery) throws AppBaseException, AppBaseException;

    void updateDelivery(Delivery delivery) throws AppBaseException, AppBaseException;

    void deleteDeliveryById(Long id) throws AppBaseException, AppBaseException;

}
