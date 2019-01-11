package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageAddDto;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageListElementDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SystemMessageConverter {

    public static SystemMessageListElementDto toSystemMessageListElement(SystemMessage systemMessage){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return SystemMessageListElementDto.builder()
                .id(systemMessage.getId())
                .message(systemMessage.getMessage())
                .title(systemMessage.getTitle())
                .messageDate(formatter.format(systemMessage.getCalendarTimestamp().getTime()))
                .build();
    }

    public static SystemMessage toSystemMessage(SystemMessageAddDto systemMessageAddDto){
        SystemMessage systemMessage=new SystemMessage();
        systemMessage.setCalendarTimestamp(Calendar.getInstance());
        systemMessage.setDeleted(false);
        systemMessage.setMessage(systemMessageAddDto.getMessage());
        systemMessage.setTitle(systemMessageAddDto.getTitle());
        return  systemMessage;
    }
}
