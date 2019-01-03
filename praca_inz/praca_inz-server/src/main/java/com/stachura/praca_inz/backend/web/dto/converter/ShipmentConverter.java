package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Shipment;
import com.stachura.praca_inz.backend.web.dto.ShipmentListElementDto;

public class ShipmentConverter {

    public static ShipmentListElementDto toShipmentListElement(Shipment shipment) {
        return ShipmentListElementDto.builder()

                .id(shipment.getId())
                .shipmentNumber(shipment.getShipmentNumber())
                .createDate(shipment.getCreateDate().getTime().toString())
                .lastUpdate(shipment.getLastUpdate().getTime().toString())
                .receiver(shipment.getRecieverWarehouse().getName())
                .sender(shipment.getSenderWarehouse().getName())
                .status(shipment.getStatus().name())
                .username(shipment.getUsername())
                .title(shipment.getTitle())
                .build();
    }
}
