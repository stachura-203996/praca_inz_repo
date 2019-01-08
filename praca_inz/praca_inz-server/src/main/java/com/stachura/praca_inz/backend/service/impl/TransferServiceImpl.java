package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.repository.interfaces.TransferRepository;
import com.stachura.praca_inz.backend.service.TransferService;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;
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
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_LIST_READ')")
    public List<TransferListElementDto> getAllTransfersForLoggedUser(String username) {
        List<Transfer> transfers = transferRepository.findAll().stream().filter(x -> x.getSenderWarehouse().getUser().getUsername().equals(username) &&
                x.getRecieverWarehouse().getUser().getUsername().equals(username) || x.getUsername().equals(username)).collect(Collectors.toList());
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

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_CREATE')")
    public void createNewTransfer(Transfer transfer)throws ServiceException {
        try {
            transferRepository.create(transfer);
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_UPDATE')")
    public void updateTransfer(Transfer transfer) throws ServiceException {
        transferRepository.update(transfer);
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
