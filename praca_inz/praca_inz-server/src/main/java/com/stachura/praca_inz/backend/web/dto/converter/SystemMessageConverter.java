package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.SystemMessageListElementDto;

public class SystemMessageConverter {

    public static SystemMessageListElementDto toSystemMessageListElement(SystemMessage systemMessage){
        return SystemMessageListElementDto.builder()
                .id(systemMessage.getId())
                .message(systemMessage.getMessage())
                .title(systemMessage.getTitle())
                .messageDate(systemMessage.getCalendarTimestamp().getTime().toString())
                .build();
    }
}
