package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.model.Confirmation;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.ConfirmationRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.ConfirmationService;
import com.stachura.praca_inz.backend.web.dto.converter.ConfirmationConverter;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationAddDto;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    @Autowired
    private ConfirmationRepository reportRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_READ')")
    public Confirmation getReportById(Long id) throws EntityNotInDatabaseException {
        Confirmation confirmation = null;
            confirmation = reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (confirmation.isDeleted()) {
            return null;
        }
        return confirmation;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ConfirmationListElementDto> getAllReports(String username) throws EntityNotInDatabaseException {
        List<Confirmation> confirmations;
        User user=userRepository.findByUsername(username).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            confirmations = Lists.newArrayList(reportRepository.findAll());
        } else{
            confirmations = Lists.newArrayList(reportRepository.findAll()).stream().filter(x->x.getSender().getOffice().getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<ConfirmationListElementDto> confirmationListElementDtos = new ArrayList<>();
        for (Confirmation a : confirmations) {
            if (!a.isDeleted()) {
                confirmationListElementDtos.add(ConfirmationConverter.toReportListElement(a));
            }
        }
        return confirmationListElementDtos;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ConfirmationListElementDto> getAllReportsForUser(String username) {
        List<Confirmation> confirmations = Lists.newArrayList(reportRepository.findAll()).stream().filter(x -> x.getSender().getUsername().equals(username)).collect(Collectors.toList());
        List<ConfirmationListElementDto> confirmationListElementDtos = new ArrayList<>();
        for (Confirmation a : confirmations) {
            if (!a.isDeleted() && !a.isDisableSender()) {
                confirmationListElementDtos.add(ConfirmationConverter.toReportListElement(a));
            }
        }
        return confirmationListElementDtos;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ConfirmationListElementDto> getAllReportsFromOthers(String username) {
        List<Confirmation> confirmations = Lists.newArrayList(reportRepository.findAll()).stream().filter(x -> x.getReciever().getUsername().equals(username)).collect(Collectors.toList());
        List<ConfirmationListElementDto> confirmationListElementDtos = new ArrayList<>();
        for (Confirmation a : confirmations) {
            if (!a.isDeleted() && !a.isDisableReciever()) {
                confirmationListElementDtos.add(ConfirmationConverter.toReportListElement(a));
            }
        }
        return confirmationListElementDtos;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_CREATE')")
    public Confirmation createNewReport(ConfirmationAddDto confirmationAddDto, String username) throws EntityNotInDatabaseException {
            User reciever=userRepository.findById(confirmationAddDto.getReciever()).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            User sender=userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            Confirmation confirmation = ConfirmationConverter.toReport(confirmationAddDto,reciever,sender);
            reportRepository.saveAndFlush(confirmation);
            return confirmation;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_UPDATE')")
    public void updateReport(Confirmation confirmation) {
        reportRepository.saveAndFlush(confirmation);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void deleteReportById(Long id) throws EntityNotInDatabaseException {
        reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableBySender(Long id) throws EntityNotInDatabaseException {
        reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDisableSender(true);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableByReciever(Long id) throws EntityNotInDatabaseException {
        reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDisableReciever(true);
    }
}
