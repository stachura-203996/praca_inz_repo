package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;


import java.util.List;

public interface TransferService {

    Transfer getTransferById(Long id);

    //    Transfer getTransferByName(String name);
    List<TransferListElementDto> getAllTransfersForLoggedUser(String username);

    List<TransferListElementDto> getAllTransfers();

    void createNewTransfer(Transfer transfer) throws ServiceException;

    void updateTransfer(Transfer transfer) throws ServiceException;

    void deleteTransferById(Long id);

    void deleteTransfer(Transfer transfer);
}
