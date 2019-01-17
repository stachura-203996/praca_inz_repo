package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.repository.SystemMessageRepository;
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
    public SystemMessage getSystemMessageById(Long id) throws AppBaseException {
        SystemMessage systemMessage = systemMessageRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (systemMessage.isDeleted()) {
            return null;
        }
        return systemMessage;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_LIST_READ')")
    public List<SystemMessageListElementDto> getAllSystemMessages() {
        List<SystemMessage> systemMessages = Lists.newArrayList(systemMessageRepository.findAll()).stream().sorted(Comparator.comparing(SystemMessage::getCreateDate).reversed()).collect(Collectors.toList());
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
    public void createNewSystemMessage(SystemMessageAddDto systemMessageAddDto) throws AppBaseException {

        systemMessageRepository.saveAndFlush(SystemMessageConverter.toSystemMessage(systemMessageAddDto));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_DELETE')")
    public void deleteSystemMessageById(Long id) throws AppBaseException {
        systemMessageRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }
}
