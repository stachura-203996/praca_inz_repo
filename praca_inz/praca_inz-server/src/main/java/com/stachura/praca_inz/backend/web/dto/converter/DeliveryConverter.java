package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;

public class DeliveryConverter {

    public static DeliveryListElementDto toDeliveryListElement(Delivery delivery){
            return DeliveryListElementDto.builder()
                    .id(delivery.getId())
                    .title(delivery.getTitle())
                    .build();
    }
}
