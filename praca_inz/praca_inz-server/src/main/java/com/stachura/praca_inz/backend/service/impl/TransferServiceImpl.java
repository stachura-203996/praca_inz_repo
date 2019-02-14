package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DeviceRepository;
import com.stachura.praca_inz.backend.repository.TransferRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.repository.WarehouseRepository;
import com.stachura.praca_inz.backend.service.TransferService;
import com.stachura.praca_inz.backend.web.dto.TransferAddDto;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.TransferConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {


    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public Transfer getTransferById(Long id) throws EntityNotInDatabaseException {
        Transfer transfer = transferRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (transfer.isDeleted()) {
            return null;
        }
        return transfer;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('TRANSFER_LIST_READ')")
    public List<TransferListElementDto> getAllTransfersForLoggedUser(String username) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        List<Transfer> transfers = Lists.newArrayList(transferRepository.findAll()).stream().filter(x -> x.getSenderWarehouse().getUsers().contains(user) &&
                x.getRecieverWarehouse().getUsers().contains(user) || x.getUser().getUsername().equals(username)).collect(Collectors.toList());
        List<TransferListElementDto> transferListElementDtos = new ArrayList<>();
        for (Transfer a : transfers) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getRecieverWarehouse());
                Hibernate.initialize(a.getSenderWarehouse());
                transferListElementDtos.add(TransferConverter.toTransferListElementDto(a));
            }
        }
        return transferListElementDtos;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public List<TransferListElementDto> getAllTransfers(String username) throws EntityNotInDatabaseException {
        List<Transfer> transfers;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (user.getUserRoles().stream().anyMatch(x -> x.getName().equals(Constants.ADMIN_ROLE))) {
            transfers = Lists.newArrayList(transferRepository.findAll());
        } else {
            transfers = Lists.newArrayList(transferRepository.findAll()).stream().filter(x -> x.getRecieverWarehouse().getOffice().getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<TransferListElementDto> transferListElementDtos = new ArrayList<>();
        for (Transfer a : transfers) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getRecieverWarehouse());
                Hibernate.initialize(a.getSenderWarehouse());
                transferListElementDtos.add(TransferConverter.toTransferListElementDto(a));
            }
        }
        return transferListElementDtos;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_CREATE')")
    public void createNewTransfer(TransferAddDto transferAddDto, String username) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Warehouse sender = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getUsers().contains(user) && x.getWarehouseType().equals(WarehouseType.USER)).findFirst().orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Warehouse reciever = warehouseRepository.findById(transferAddDto.getRecieverWarehouseId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Device device= deviceRepository.findById(transferAddDto.getDeviceId()).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        device.setWarehouse(reciever);
        device.setCompany(reciever.getOffice().getDepartment().getCompany());
        deviceRepository.saveAndFlush(device);
        transferRepository.saveAndFlush(TransferConverter.toTransfer(transferAddDto, user, sender, reciever, device));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_UPDATE')")
    public void updateTransfer(Transfer transfer) {
        transferRepository.saveAndFlush(transfer);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransferById(Long id) throws EntityNotInDatabaseException {
        transferRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransfer(Transfer transfer) throws EntityNotInDatabaseException {
        transferRepository.findById(transfer.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }
}
