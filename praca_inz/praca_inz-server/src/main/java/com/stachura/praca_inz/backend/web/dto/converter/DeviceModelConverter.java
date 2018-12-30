package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.web.dto.DeviceModelListElementDto;

public class DeviceModelConverter {


    public static DeviceModelListElementDto toDeviceTypeListElement(DeviceModel type){
        return DeviceModelListElementDto.builder()
                .id(type.getId())
                .name(type.getName())
                .build();

    }

}
