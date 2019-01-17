package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.ExternalTransfer;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.ExternalTransferRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.ExternalTransferService;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeliveryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalTransferServiceImpl implements ExternalTransferService {

    @Autowired
    private ExternalTransferRepository externalTransferRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_READ')")
    public ExternalTransfer getDeliveryById(Long id) throws EntityNotInDatabaseException {
        ExternalTransfer externalTransfer = externalTransferRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (externalTransfer.isDeleted()) {
            return null;
        }
        return externalTransfer;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_LIST_READ')")
    public List<DeliveryListElementDto> getAllDeliveries(String username) throws EntityNotInDatabaseException {
        List<ExternalTransfer> deliveries;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (user.getUserRoles().stream().anyMatch(x -> x.getName().equals(Constants.ADMIN_ROLE))) {
            deliveries = Lists.newArrayList(externalTransferRepository.findAll());
        } else {
            deliveries = Lists.newArrayList(externalTransferRepository.findAll()).stream().filter(x -> x.getSenderWarehouse().getOffice().getDepartment().getCompany()
                    .getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<DeliveryListElementDto> companiesDto = new ArrayList<>();
        for (ExternalTransfer a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_LIST_READ')")
    public List<DeliveryListElementDto> getAllDeliveriesForWarehouseman(String username) throws EntityNotInDatabaseException {
        Long id = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).getId();
        List<ExternalTransfer> deliveries = Lists.newArrayList(externalTransferRepository.findAll()).stream().filter(x -> x.getRecieverWarehouse().getUser().getId().equals(id)).collect(Collectors.toList());
        List<DeliveryListElementDto> companiesDto = new ArrayList<>();
        for (ExternalTransfer a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_CREATE')")
    public void createNewDelivery(ExternalTransfer externalTransfer) throws AppBaseException {
        try {
            externalTransferRepository.saveAndFlush(externalTransfer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_UPDATE')")
    public void updateDelivery(ExternalTransfer externalTransfer) throws AppBaseException {
        try {
            externalTransferRepository.saveAndFlush(externalTransfer);
        } catch (OptimisticLockException e) {
            new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_DELETE')")
    public void deleteDeliveryById(Long id) throws AppBaseException {
        externalTransferRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }


}
