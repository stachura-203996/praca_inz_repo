package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.repository.interfaces.TransferRepository;
import com.stachura.praca_inz.backend.service.TransferService;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceConverter;
import com.stachura.praca_inz.backend.web.dto.converter.TransferConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {


    @Autowired
    private TransferRepository transferRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public Transfer getTransferById(Long id) {
        Transfer transfer = transferRepository.find(id);
        if (transfer.isDeleted()) {
            return null;
        }
        return transfer;
    }

    @Override
    public List<TransferListElementDto> getAllTransfersForLoggedUser(String username) {
        List<Transfer> transfers = transferRepository.findAll().stream().filter(x -> x.getSenderWarehouse().getUser().getUsername().equals(username) &&
                x.getRecieverWarehouse().getUser().getUsername().equals(username)).collect(Collectors.toList());
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
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public List<TransferListElementDto> getAllTransfers() {
        List<Transfer> transfers = transferRepository.findAll();
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

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_CREATE')")
    public void createNewTransfer(Transfer transfer) {
        try {
            transferRepository.create(transfer);

        } catch (EntityException e) {

        }

    }

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_UPDATE')")
    public Transfer updateTransfer(Transfer transfer) {
        Transfer tmp = new Transfer();
        try {
            if (!transferRepository.find(transfer.getId()).isDeleted()) {
                transfer = transferRepository.update(transfer);
            }
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransferById(Long id) {
        transferRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransfer(Transfer transfer) {
        transferRepository.find(transfer.getId()).setDeleted(true);
    }
}
