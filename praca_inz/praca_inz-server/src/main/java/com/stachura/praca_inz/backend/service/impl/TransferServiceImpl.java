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
        return transferRepository.find(id);
    }

    @Override
    public List<TransferListElementDto> getAllTransfersForLoggedUser(String username) {
        List<Transfer> transfers = transferRepository.findAll().stream().filter(x -> x.getSenderWarehouse().getUser().getUsername().equals(username) &&
                x.getRecieverWarehouse().getUser().getUsername().equals(username)).collect(Collectors.toList());
        List<TransferListElementDto> transferListElementDtos = new ArrayList<>();
        for (Transfer a : transfers) {
            Hibernate.initialize(a.getRecieverWarehouse());
            Hibernate.initialize(a.getSenderWarehouse());
            transferListElementDtos.add(TransferConverter.toTransferListElementDto(a));
        }
        return transferListElementDtos;
    }


    /*@Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_READ') and hasAuthority('DEPARTMENT_READ')")
    public Transfer getCompanyById(String name) {
        return transferRepository.find(name);
    }*/


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public List<TransferListElementDto> getAllTransfers() {
        List<Transfer> transfers = transferRepository.findAll();
        List<TransferListElementDto> transferListElementDtos = new ArrayList<>();
        for (Transfer a : transfers) {
            Hibernate.initialize(a.getRecieverWarehouse());
            Hibernate.initialize(a.getSenderWarehouse());
            transferListElementDtos.add(TransferConverter.toTransferListElementDto(a));
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
            transfer = transferRepository.update(transfer);
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransferById(Long id) {
        transferRepository.remove(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransfer(Transfer transfer) {
        transferRepository.remove(transfer);
    }
}
