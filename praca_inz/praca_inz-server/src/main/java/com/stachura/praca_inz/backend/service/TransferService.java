package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.web.dto.TransferAddDto;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;

import java.util.List;

public interface TransferService {

    Transfer getTransferById(Long id) throws AppBaseException;

    List<TransferListElementDto> getAllTransfersForLoggedUser(String username);

    List<TransferListElementDto> getAllTransfers(String username) throws AppBaseException;

    void createNewTransfer(TransferAddDto transferAddDto,String username) throws AppBaseException;

    void updateTransfer(Transfer transfer) throws AppBaseException;

    void deleteTransferById(Long id) throws AppBaseException;

    void deleteTransfer(Transfer transfer) throws AppBaseException;
}
