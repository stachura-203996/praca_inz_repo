package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;

public class DeviceTypeConverter {

    public static DeviceTypeListElementDto toDeviceTypeListElement(DeviceType deviceType){
      return DeviceTypeListElementDto.builder().name(deviceType.getName()).id(deviceType.getId()).build();
    }
}
