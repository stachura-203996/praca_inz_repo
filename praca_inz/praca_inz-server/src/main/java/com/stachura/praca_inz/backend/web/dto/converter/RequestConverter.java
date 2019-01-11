package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.model.enums.RequestType;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceModelRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import com.stachura.praca_inz.backend.web.dto.request.*;

import java.util.*;

public class RequestConverter {

    public static RequestListElementDto toRequestListElement(Request request) {
        return RequestListElementDto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .username(request.getUser().getUsername())
                .status(request.getStatus().name())
                .type(request.getRequestType().name())
                .recieverWarehouseName(request.getRecieverWarehouse().getName())
                .senderWarehouseName(request.getSenderWarehouse()==null?" ":request.getSenderWarehouse().getName())
                .createDate(request.getCreateDate().getTime().toString())
                .build();
    }

    public static RequestViewDto toRequestView(Request request) {
        return RequestViewDto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .deviceModelName(request.getDeviceModel().getName())
                .description(request.getDescription())
                .username(request.getUser().getUsername())
                .status(request.getStatus().name())
                .type(request.getRequestType().name())
                .recieverWarehouseName(request.getRecieverWarehouse().getName())
                .senderWarehouseName(request.getSenderWarehouse()==null?" ":request.getSenderWarehouse().getName())
                .createDate(request.getCreateDate().getTime().toString())
                .amount(request.getAmount())
                .build();
    }

    public static Request toRequest(TransferRequestAddDto transferRequestAddDto, DeviceRepository deviceRepository, WarehouseRepository warehouseRepository, User user) {
        Request request = new Request();
        request.setDeleted(false);
        request.setDescription(transferRequestAddDto.getDescription());
        Device device = deviceRepository.find(transferRequestAddDto.getDeviceId());
        request.setDeviceModel(device.getDeviceModel());
        request.setRequestType(RequestType.TRANSFER_REQUEST);
        request.setRecieverWarehouse(warehouseRepository.find(transferRequestAddDto.getRecieverWarehouseId()));
        request.setSenderWarehouse(device.getWarehouse());
        request.setStatus(Status.WAITING);
        request.setTitle(transferRequestAddDto.getTitle());
        request.setUser(user);
        request.setCreateDate(Calendar.getInstance());
        List<Device> deviceList=new ArrayList<>();
        deviceList.add(device);
        request.setDevices(deviceList);
        return request;
    }

    public static Request toRequest(DeviceRequestAddDto deviceRequestAddDto, DeviceModelRepository deviceModelRepository, WarehouseRepository warehouseRepository, User user) {
        Request request = new Request();
        request.setDeleted(false);
        request.setAmount(deviceRequestAddDto.getAmount());
        request.setDescription(deviceRequestAddDto.getDescription());
        DeviceModel deviceModel = deviceModelRepository.find(deviceRequestAddDto.getDeviceModelId());
        request.setDeviceModel(deviceModel);
        request.setRequestType(RequestType.DEVICE_REQUEST);
        request.setRecieverWarehouse(warehouseRepository.findUserWarehouse(user.getId()));
        request.setStatus(Status.WAITING);
        request.setTitle(deviceRequestAddDto.getTitle());
        List<Device> deviceList=new ArrayList<>();
        request.setDevices(deviceList);
        request.setUser(user);
        request.setCreateDate(Calendar.getInstance());
        return request;
    }

    public static Request toRequest(DeliveryRequestAddDto deliveryRequestAddDto, DeviceModelRepository deviceModelRepository, WarehouseRepository warehouseRepository, User user) {
        Request request = new Request();
        request.setDeleted(false);
        request.setAmount(deliveryRequestAddDto.getAmount());
        request.setDescription(deliveryRequestAddDto.getDescription());
        DeviceModel deviceModel = deviceModelRepository.find(deliveryRequestAddDto.getDeviceModelId());
        request.setDeviceModel(deviceModel);
        request.setRequestType(RequestType.DELIVERY_REQUEST);
        request.setRecieverWarehouse(warehouseRepository.findOfficeWarehouse(user.getId()));
        request.setStatus(Status.WAITING);
        request.setTitle(deliveryRequestAddDto.getTitle());
        request.setUser(user);
        request.setCreateDate(Calendar.getInstance());
        return request;
    }

    public static Request toRequest(ShipmentRequestAddDto shipmentRequestAddDto, DeviceRepository deviceRepository, WarehouseRepository warehouseRepository, User user) {
        Request request = new Request();
        request.setDeleted(false);
        request.setDescription(shipmentRequestAddDto.getDescription());
        Device device = deviceRepository.find(shipmentRequestAddDto.getDeviceId());
        request.setRequestType(RequestType.SHIPMENT_REQUEST);
        request.setRecieverWarehouse(warehouseRepository.find(shipmentRequestAddDto.getRecieverWarehouseId()));
        request.setSenderWarehouse(device.getWarehouse());
        request.setStatus(Status.WAITING);
        request.setTitle(shipmentRequestAddDto.getTitle());
        request.setUser(user);
        request.setCreateDate(Calendar.getInstance());
        return request;
    }
}

