package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageAddDto;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageListElementDto;

import java.util.List;

public interface SystemMessageService {

    SystemMessage getSystemMessageById(Long id) throws AppBaseException;

    List<SystemMessageListElementDto> getAllSystemMessages();

    void createNewSystemMessage(SystemMessageAddDto systemMessageAddDto) throws AppBaseException;

    void deleteSystemMessageById(Long id) throws AppBaseException;

}
