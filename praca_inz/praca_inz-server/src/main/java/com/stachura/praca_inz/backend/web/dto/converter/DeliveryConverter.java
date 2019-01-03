package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;

public class DeliveryConverter {

    public static DeliveryListElementDto toDeliveryListElement(Delivery delivery){
            return DeliveryListElementDto.builder()
                    .id(delivery.getId())
                    .deliveryNumber(delivery.getDeliveryNumber())
                    .createDate(delivery.getCreateDate().getTime().toString())
                    .lastUpdate(delivery.getLastUpdate().getTime().toString())
                    .receiver(delivery.getRecieverWarehouse().getName())
                    .sender(delivery.getSenderWarehouse().getName())
                    .status(delivery.getStatus().name())
                    .username(delivery.getUsername())
                    .title(delivery.getTitle())
                    .build();
    }
}
