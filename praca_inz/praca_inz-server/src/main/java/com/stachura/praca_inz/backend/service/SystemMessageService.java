package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageAddDto;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageListElementDto;

import java.util.List;

public interface SystemMessageService {

    SystemMessage getSystemMessageById(Long id) throws ServiceException;

    List<SystemMessageListElementDto> getAllSystemMessages();

    void createNewSystemMessage(SystemMessageAddDto systemMessageAddDto) throws ServiceException;

    void deleteSystemMessageById(Long id) throws ServiceException;

}
