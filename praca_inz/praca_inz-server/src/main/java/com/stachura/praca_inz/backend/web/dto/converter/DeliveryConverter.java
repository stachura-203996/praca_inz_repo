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
                    .receiver(delivery.getRecieverWarehouse().getName())
                    .sender(delivery.getSenderWarehouse().getName())
                    .confirmed(delivery.isConfirmed())
                    .companyId(delivery.getDeviceModel().getCompany().getId())
                    .serialNumber(delivery.getSerialNumber())
                    .deviceModelId(delivery.getDeviceModel().getId())
                    .warehouseId(delivery.getSenderWarehouse().getId())
                    .deviceModelName(delivery.getDeviceModel().getName())
                    .title(delivery.getTitle())
                    .build();
    }
}
