package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;

public class DeviceConverter {

    public static DeviceListElementDto toDeviceListElementDto(Device device){
        return DeviceListElementDto.builder()
                .id(device.getId())
                .name(device.getName())
                .username(device.getWarehouse().getUser().getUsername())
                .serialNumber(device.getSerialNumber())
                .deviceTypeName(device.getDeviceType().getName())
                .build();
    }
}
