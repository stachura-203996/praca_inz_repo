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

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    private SystemMessageRepository systemMessageRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_READ')")
    public SystemMessage getSystemMessageById(Long id) {
        return systemMessageRepository.find(id);
    }

//    @Override
//    @Transactional(readOnly = true)
////    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_READ') and hasAuthority('DEPARTMENT_READ')")
//    public SystemMessage getCompanyById(String name) {
//        return systemMessageRepository.find(name);
//    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_LIST_READ')")
    public List<SystemMessageListElementDto> getAllSystemMessages() {
        List<SystemMessage> systemMessages = systemMessageRepository.findAll();
        List<SystemMessageListElementDto> systemMessageListElementDtos = new ArrayList<>();
        for (SystemMessage a : systemMessages) {
            systemMessageListElementDtos.add(SystemMessageConverter.toSystemMessageListElement(a));
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
            tmp = systemMessageRepository.update(systemMessage);
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_DELETE')")
    public void deleteSystemMessageById(Long id) {
        systemMessageRepository.remove(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SYSTEM_MESSAGE_DELETE')")
    public void deleteSystemMessage(SystemMessage systemMessage) {
        systemMessageRepository.remove(systemMessage);
    }
}
