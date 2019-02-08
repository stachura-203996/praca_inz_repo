package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.web.dto.TransferAddDto;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;

import java.util.List;

public interface TransferService {

    Transfer getTransferById(Long id) throws SystemBaseException;

    List<TransferListElementDto> getAllTransfersForLoggedUser(String username);

    List<TransferListElementDto> getAllTransfers(String username) throws SystemBaseException;

    void createNewTransfer(TransferAddDto transferAddDto,String username) throws SystemBaseException;

    void updateTransfer(Transfer transfer) throws SystemBaseException;

    void deleteTransferById(Long id) throws SystemBaseException;

    void deleteTransfer(Transfer transfer) throws SystemBaseException;
}
