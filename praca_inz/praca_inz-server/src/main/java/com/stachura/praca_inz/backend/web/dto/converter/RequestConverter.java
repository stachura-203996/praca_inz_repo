package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.model.enums.RequestType;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DeviceModelRepository;
import com.stachura.praca_inz.backend.repository.DeviceRepository;
import com.stachura.praca_inz.backend.repository.WarehouseRepository;
import com.stachura.praca_inz.backend.web.dto.request.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class RequestConverter {

    public static RequestListElementDto toRequestListElement(Request request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return RequestListElementDto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .username(request.getUser().getUsername())
                .status(request.getStatus().name())
                .type(request.getRequestType().name())
                .recieverWarehouseName(request.getRecieverWarehouse().getName())
                .senderWarehouseName(request.getSenderWarehouse() == null ? " " : request.getSenderWarehouse().getName())
                .createDate(formatter.format(request.getCreateDate().getTime()))
                .build();
    }

    public static RequestViewDto toRequestView(Request request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return RequestViewDto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .deviceModelName(request.getDeviceModel().getName())
                .description(request.getDescription())
                .username(request.getUser().getUsername())
                .status(request.getStatus().name())
                .type(request.getRequestType().name())
                .recieverWarehouseName(request.getRecieverWarehouse().getName())
                .senderWarehouseName(request.getSenderWarehouse() == null ? " " : request.getSenderWarehouse().getName())
                .createDate(formatter.format(request.getCreateDate().getTime()))
                .amount(request.getAmount())
                .build();
    }

    public static Request toRequest(TransferRequestAddDto transferRequestAddDto, Device device, Warehouse recieverWarehouse, User user) {
        Request request = new Request();
        request.setDeleted(false);
        request.setDescription(transferRequestAddDto.getDescription());
        request.setDeviceModel(device.getDeviceModel());
        request.setRequestType(RequestType.TRANSFER_REQUEST);
        request.setRecieverWarehouse(recieverWarehouse);
        request.setSenderWarehouse(device.getWarehouse());
        request.setStatus(Status.WAITING);
        request.setTitle(transferRequestAddDto.getTitle());
        request.setUser(user);
        request.setCreateDate(Calendar.getInstance());
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);
        request.setDevices(deviceList);
        return request;
    }

    public static Request toRequest(DeviceRequestAddDto deviceRequestAddDto, DeviceModel deviceModel, Warehouse recieverWarehouse, User user) {
        Request request = new Request();
        request.setDeleted(false);
        request.setAmount(deviceRequestAddDto.getAmount());
        request.setDescription(deviceRequestAddDto.getDescription());
        request.setDeviceModel(deviceModel);
        request.setRequestType(RequestType.DEVICE_REQUEST);
        request.setRecieverWarehouse(recieverWarehouse);
        request.setStatus(Status.WAITING);
        request.setTitle(deviceRequestAddDto.getTitle());
        List<Device> deviceList = new ArrayList<>();
        request.setDevices(deviceList);
        request.setUser(user);
        request.setCreateDate(Calendar.getInstance());
        return request;
    }

}

