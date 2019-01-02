package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Parameter;
import com.stachura.praca_inz.backend.web.dto.ParameterListElementDto;

public class ParameterConverter {

    public static ParameterListElementDto toParameterListElementDto(Parameter parameter){
        return ParameterListElementDto.builder()
                .id(parameter.getId())
                .name(parameter.getName())
                .value(parameter.getValue())
                .build();
    }
}
