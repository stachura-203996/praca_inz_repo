package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelListElementDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelViewDto;

public class DeviceModelConverter {


    public static DeviceModelListElementDto toDeviceModelListElement(DeviceModel deviceModel){
        return DeviceModelListElementDto.builder()
                .id(deviceModel.getId())
                .name(deviceModel.getName())
                .companyName(deviceModel.getCompany().getName())
                .manufacture(deviceModel.getManufacture())
                .deviceTypeName(deviceModel.getDeviceType().getName())
                .build();

    }

    public static DeviceModelViewDto toDeviceModelViewDto(DeviceModel deviceModel){
        return DeviceModelViewDto.builder()
                .id(deviceModel.getId())
                .manufacture(deviceModel.getManufacture())
                .name(deviceModel.getName())
                .type(deviceModel.getDeviceType().getName())
                .cost(deviceModel.getCost())
                .numberOfDevices(String.valueOf(deviceModel.getDevices().size()))
                .companyName(deviceModel.getCompany().getName())
                .build();
    }

}
