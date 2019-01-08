package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;

import java.util.Date;

public class TransferConverter {
    public static TransferListElementDto toTransferListElementDto(Transfer transfer) {
        return TransferListElementDto.builder()
                .id(transfer.getId())
                .title(transfer.getTitle())
                .date(transfer.getTransferDate().getTime().toString())
                .senderWarehouseName(transfer.getSenderWarehouse().getName())
                .recieverWarehouseName(transfer.getRecieverWarehouse().getName())
                .deviceName(transfer.getDevice().getName())
                .deviceSerialNumber(transfer.getDevice().getSerialNumber())
                .build();
    }
}
