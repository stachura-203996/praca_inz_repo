package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.repository.interfaces.SystemMessageRepository;
import com.stachura.praca_inz.backend.service.SystemMessageService;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.SystemMessageListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import com.stachura.praca_inz.backend.web.dto.converter.SystemMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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
    public void createNewSystemMessage(SystemMessage systemMessage) {
        try {
            systemMessageRepository.create(systemMessage);
        } catch (EntityException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_UPDATE')")
    public SystemMessage updateSystemMessage(SystemMessage systemMessage) {
        SystemMessage tmp = new SystemMessage();
        try {
            if(!systemMessageRepository.find(systemMessage.getId()).isDeleted()) {
                tmp = systemMessageRepository.update(systemMessage);
            }
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_DELETE')")
    public void deleteSystemMessageById(Long id) {
        systemMessageRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_DELETE')")
    public void deleteSystemMessage(SystemMessage systemMessage) {
        systemMessageRepository.find(systemMessage.getId()).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_LIST_READ')")
    public List<SystemMessageListElementDto> getLast4SystemMessages() {
        List<SystemMessage> systemMessages = systemMessageRepository.findAll().stream().sorted(Comparator.comparing(SystemMessage::getCalendarTimestamp).reversed()).collect(Collectors.toList());
        List<SystemMessageListElementDto> systemMessageListElementDtos = new ArrayList<>();
        int i=0;
        for (SystemMessage a : systemMessages) {
            if (!a.isDeleted()&&i<3) {
                systemMessageListElementDtos.add(SystemMessageConverter.toSystemMessageListElement(a));
                i++;
            }
        }
        return systemMessageListElementDtos;
    }
}
