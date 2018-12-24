package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;

public class DeviceTypeConverter {


    public static DeviceTypeListElementDto toDeviceTypeListElement(DeviceType type){
        return DeviceTypeListElementDto.builder()
                .id(type.getId())
                .name(type.getName())
                .build();

    }

}
