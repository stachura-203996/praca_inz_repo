package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.web.dto.TransferAddDto;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransferConverter {

    //LIST
    public static TransferListElementDto toTransferListElementDto(Transfer transfer) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return TransferListElementDto.builder()
                .id(transfer.getId())
                .title(transfer.getTitle())
                .date(formatter.format(transfer.getCreateDate().getTime()))
                .senderWarehouseName(transfer.getSenderWarehouse().getName())
                .recieverWarehouseName(transfer.getRecieverWarehouse().getName())
                .deviceModelName(transfer.getDevice().getDeviceModel().getName())
                .deviceSerialNumber(transfer.getDevice().getSerialNumber())
                .build();

    }

    //ADD
    public static Transfer toTransfer(TransferAddDto transferAddDto, String username, Warehouse sender, Warehouse reciever, Device device) {
        Transfer transfer = new Transfer();
        transfer.setUsername(username);
        transfer.setCreateDate(Calendar.getInstance());
        transfer.setTitle(transferAddDto.getTitle());
        transfer.setStatus(Status.TRANSFERED);
        transfer.setSenderWarehouse(sender);
        transfer.setRecieverWarehouse(reciever);
        transfer.setDevice(device);
        transfer.setDescription("Admin transfer");
        return transfer;
    }

}