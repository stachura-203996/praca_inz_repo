package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.ExternalTransfer;
import com.stachura.praca_inz.backend.web.dto.ExternalTransferListElementDto;

import java.text.SimpleDateFormat;

public class DeliveryConverter {

    //LIST
    public static ExternalTransferListElementDto toDeliveryListElement(ExternalTransfer externalTransfer){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ExternalTransferListElementDto.builder()
                    .id(externalTransfer.getId())
                    .deliveryNumber(externalTransfer.getExternalTransferNumber())
                    .createDate(formatter.format(externalTransfer.getCreateDate().getTime()))
                    .receiver(externalTransfer.getRecieverWarehouse().getName())
                    .sender(externalTransfer.getSenderWarehouse().getName())
                    .confirmed(externalTransfer.isConfirmed())
                    .companyId(externalTransfer.getDeviceModel().getCompany().getId())
                    .serialNumber(externalTransfer.getSerialNumber())
                    .deviceModelId(externalTransfer.getDeviceModel().getId())
                    .warehouseId(externalTransfer.getSenderWarehouse().getId())
                    .deviceModelName(externalTransfer.getDeviceModel().getName())
                    .lastUpdate(formatter.format(externalTransfer.getConfirmDate().getTime()))
                    .title(externalTransfer.getTitle())
                    .build();
    }
}
