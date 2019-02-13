package com.stachura.praca_inz.backend.service;


import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.model.ExternalTransfer;
import com.stachura.praca_inz.backend.web.dto.ExternalTransferListElementDto;

import java.util.List;

public interface ExternalTransferService {

    ExternalTransfer getDeliveryById(Long id) throws SystemBaseException, SystemBaseException;

    List<ExternalTransferListElementDto> getAllDeliveries(String username) throws SystemBaseException, SystemBaseException;

    List<ExternalTransferListElementDto> getAllDeliveriesForWarehouseman(String username) throws SystemBaseException, SystemBaseException;

    void createNewDelivery(ExternalTransfer externalTransfer) throws SystemBaseException, SystemBaseException;

    void updateDelivery(ExternalTransfer externalTransfer) throws SystemBaseException, SystemBaseException;

    void deleteDeliveryById(Long id) throws SystemBaseException, SystemBaseException;

    void confirmExternalTransfer(Long id) throws EntityNotInDatabaseException;
}
