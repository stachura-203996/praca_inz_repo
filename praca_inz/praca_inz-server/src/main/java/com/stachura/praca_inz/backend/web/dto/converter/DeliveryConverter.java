package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;

import java.text.SimpleDateFormat;

public class DeliveryConverter {

    public static DeliveryListElementDto toDeliveryListElement(Delivery delivery){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return DeliveryListElementDto.builder()
                    .id(delivery.getId())
                    .deliveryNumber(delivery.getDeliveryNumber())
                    .createDate(formatter.format(delivery.getCreateDate().getTime()))
                    .lastUpdate(formatter.format(delivery.getLastUpdate().getTime()))
                    .receiver(delivery.getRecieverWarehouse().getName())
                    .sender(delivery.getSenderWarehouse().getName())
                    .status(delivery.getStatus().name())
                    .username(delivery.getUsername())
                    .title(delivery.getTitle())
                    .build();
    }
}
