package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.web.dto.SystemMessageListElementDto;

import java.util.List;

public interface SystemMessageService {

    SystemMessage getSystemMessageById(Long id);

//    SystemMessage getSystemMessageByName(String name);

    List<SystemMessageListElementDto> getAllSystemMessages();

    void createNewSystemMessage(SystemMessage systemMessage);

    SystemMessage updateSystemMessage(SystemMessage systemMessage);

    void deleteSystemMessageById(Long id);

    void deleteSystemMessage(SystemMessage systemMessage);

    List<SystemMessageListElementDto> getLast4SystemMessages();
}
