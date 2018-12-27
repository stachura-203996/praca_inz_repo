package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Shipment;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;
import com.stachura.praca_inz.backend.web.dto.ShipmentListElementDto;

public class ShipmentConverter {

    public static ShipmentListElementDto toShipmentListElement(Shipment shipment) {
        return ShipmentListElementDto.builder()

                .id(shipment.getId())
                .deliveryNumber(shipment.getDeliveryNumber())
                .title(shipment.getTitle())
                .username(shipment.getUsername())
                .status(shipment.getStatus().name())
                .senderWarehouseName(shipment.getSenderWarehouse().getName())
                .recieverWarehouseName(shipment.getRecieverWarehouse().getName())
                .utilTimestamp(shipment.getUtilTimestamp().toString())
                .build();
    }
}
