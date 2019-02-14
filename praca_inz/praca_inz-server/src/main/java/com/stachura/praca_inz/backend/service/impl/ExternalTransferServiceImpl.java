package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.model.ExternalTransfer;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.ExternalTransferRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.ExternalTransferService;
import com.stachura.praca_inz.backend.web.dto.ExternalTransferListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeliveryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalTransferServiceImpl implements ExternalTransferService {

    @Autowired
    private ExternalTransferRepository externalTransferRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('EXTERNAL_TRANSFER_READ')")
    public ExternalTransfer getDeliveryById(Long id) throws EntityNotInDatabaseException {
        ExternalTransfer externalTransfer = externalTransferRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (externalTransfer.isDeleted()) {
            return null;
        }
        return externalTransfer;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('EXTERNAL_TRANSFER_LIST_READ')")
    public List<ExternalTransferListElementDto> getAllDeliveries(String username) throws EntityNotInDatabaseException {
        List<ExternalTransfer> deliveries;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (user.getUserRoles().stream().anyMatch(x -> x.getName().equals(Constants.ADMIN_ROLE))) {
            deliveries = Lists.newArrayList(externalTransferRepository.findAll());
        } else {
            deliveries = Lists.newArrayList(externalTransferRepository.findAll()).stream().filter(x -> x.getSenderWarehouse().getOffice().getDepartment().getCompany()
                    .getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<ExternalTransferListElementDto> companiesDto = new ArrayList<>();
        for (ExternalTransfer a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('EXTERNAL_TRANSFER_LIST_READ')")
    public List<ExternalTransferListElementDto> getAllDeliveriesForWarehouseman(String username) throws EntityNotInDatabaseException {
        User user =userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        List<ExternalTransfer> deliveries = Lists.newArrayList(externalTransferRepository.findAll()).stream().filter(x -> x.getRecieverWarehouse().getUsers().contains(user)).collect(Collectors.toList());
        List<ExternalTransferListElementDto> companiesDto = new ArrayList<>();
        for (ExternalTransfer a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('EXTERNAL_TRANSFER_CREATE')")
    public void createNewDelivery(ExternalTransfer externalTransfer)   {
            externalTransferRepository.saveAndFlush(externalTransfer);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('EXTERNAL_TRANSFER_UPDATE')")
    public void updateDelivery(ExternalTransfer externalTransfer) {
            externalTransferRepository.saveAndFlush(externalTransfer);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('EXTERNAL_TRANSFER_DELETE')")
    public void deleteDeliveryById(Long id) throws EntityNotInDatabaseException {
        externalTransferRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('EXTERNAL_TRANSFER_CONFIRM')")
    public void confirmExternalTransfer(Long id) throws EntityNotInDatabaseException {
        ExternalTransfer externalTransfer= externalTransferRepository.findById(id).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        externalTransfer.setConfirmed(true);
        externalTransfer.setConfirmDate(Calendar.getInstance());
        externalTransferRepository.saveAndFlush(externalTransfer);
    }


}
