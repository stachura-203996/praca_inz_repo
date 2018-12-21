package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;

import java.util.List;

public interface DeliveryService {

    Delivery getDeliveryById(Long id);

//    Delivery getDeliveryByName(String name);

    List<DeliveryListElementDto> getAllDeliveries();

    void createNewDelivery(Delivery delivery);

    Delivery updateDelivery(Delivery delivery);

    void deleteDeliveryById(Long id);

    void deleteDelivery(Delivery delivery);
}
