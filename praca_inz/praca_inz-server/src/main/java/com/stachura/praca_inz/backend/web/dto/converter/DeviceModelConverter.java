package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.web.dto.DeviceModelListElementDto;
import com.stachura.praca_inz.backend.web.dto.DeviceModelViewDto;

public class DeviceModelConverter {


    public static DeviceModelListElementDto toDeviceModelListElement(DeviceModel type){
        return DeviceModelListElementDto.builder()
                .id(type.getId())
                .name(type.getName())
                .build();

    }

    public static DeviceModelViewDto toDeviceModelViewDto(DeviceModel deviceModel){
        return DeviceModelViewDto.builder()
                .id(deviceModel.getId())
                .manufacture(deviceModel.getManufacture())
                .name(deviceModel.getName())
                .numberOfDevices(String.valueOf(deviceModel.getDevices().size()))
                .owner(deviceModel.getCompany().getName())
                .build();
    }

}
