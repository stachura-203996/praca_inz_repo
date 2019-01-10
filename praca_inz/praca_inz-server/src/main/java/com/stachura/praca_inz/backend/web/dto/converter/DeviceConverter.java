package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;

public class DeviceConverter {

    public static DeviceListElementDto toDeviceListElementDto(Device device){
        return DeviceListElementDto.builder()
                .id(device.getId())
                .username(device.getWarehouse().getUser().getUsername())
                .serialNumber(device.getSerialNumber())
                .deviceModel(device.getDeviceModel().getName())
                .deviceTypeName(device.getDeviceModel().getDeviceType().getName())
                .manufacture(device.getDeviceModel().getManufacture())
                .lastUpdate(device.getLastUpdate().getTime().toString())
                .status(device.getStatus().name())
                .location(device.getCompany().getName()+
                        " > " + device.getWarehouse().getOffice().getDepartment().getName()+
                        " > " + device.getWarehouse().getOffice().getName()+
                        " > " + device.getWarehouse().getName())
                .build();
    }
}
