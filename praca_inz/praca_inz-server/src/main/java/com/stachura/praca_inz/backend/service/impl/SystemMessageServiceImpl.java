package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.repository.interfaces.SystemMessageRepository;
import com.stachura.praca_inz.backend.service.SystemMessageService;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageAddDto;
import com.stachura.praca_inz.backend.web.dto.system_message.SystemMessageListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.SystemMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    private SystemMessageRepository systemMessageRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_READ')")
    public SystemMessage getSystemMessageById(Long id) {
        SystemMessage systemMessage = systemMessageRepository.find(id);
        if (systemMessage.isDeleted()) {
            return null;
        }
        return systemMessage;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_LIST_READ')")
    public List<SystemMessageListElementDto> getAllSystemMessages() {
        List<SystemMessage> systemMessages = systemMessageRepository.findAll().stream().sorted(Comparator.comparing(SystemMessage::getCalendarTimestamp).reversed()).collect(Collectors.toList());
        List<SystemMessageListElementDto> systemMessageListElementDtos = new ArrayList<>();
        for (SystemMessage a : systemMessages) {
            if (!a.isDeleted()) {
                systemMessageListElementDtos.add(SystemMessageConverter.toSystemMessageListElement(a));
            }
        }
        return systemMessageListElementDtos;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_CREATE')")
    public void createNewSystemMessage(SystemMessageAddDto systemMessageAddDto) throws ServiceException{
        try {
            systemMessageRepository.create(SystemMessageConverter.toSystemMessage(systemMessageAddDto));
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_DELETE')")
    public void deleteSystemMessageById(Long id) {
        systemMessageRepository.find(id).setDeleted(true);
    }
}
