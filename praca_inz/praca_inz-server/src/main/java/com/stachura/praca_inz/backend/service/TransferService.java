package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;


import java.util.List;

public interface TransferService {

    Transfer getTransferById(Long id) throws ServiceException;

    List<TransferListElementDto> getAllTransfersForLoggedUser(String username);

    List<TransferListElementDto> getAllTransfers(String username) throws ServiceException;

    void createNewTransfer(Transfer transfer) throws ServiceException;

    void updateTransfer(Transfer transfer) throws ServiceException;

    void deleteTransferById(Long id) throws ServiceException;

    void deleteTransfer(Transfer transfer) throws ServiceException;
}
