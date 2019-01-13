package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.model.Parameter;
import com.stachura.praca_inz.backend.web.dto.device.ParameterListElementDto;

public class ParameterConverter {

    public static ParameterListElementDto toParameterListElementDto(Parameter parameter){
        return ParameterListElementDto.builder()
                .id(parameter.getId())
                .name(parameter.getName())
                .value(parameter.getValue())
                .build();
    }


    public static Parameter toParameter(ParameterListElementDto parameterListElementDto, DeviceModel deviceModel){
        Parameter parameter=new Parameter();
        parameter.setName(parameterListElementDto.getName());
        parameter.setValue(parameterListElementDto.getValue());
        parameter.setDeviceModel(deviceModel);
      return parameter;
    }
}
