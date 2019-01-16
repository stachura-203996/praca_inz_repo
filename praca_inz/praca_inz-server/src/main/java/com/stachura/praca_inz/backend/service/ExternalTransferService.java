package com.stachura.praca_inz.backend.service;


import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.ExternalTransfer;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;

import java.util.List;

public interface ExternalTransferService {

    ExternalTransfer getDeliveryById(Long id) throws AppBaseException, AppBaseException;

    List<DeliveryListElementDto> getAllDeliveries(String username) throws AppBaseException, AppBaseException;

    List<DeliveryListElementDto> getAllDeliveriesForWarehouseman(String username) throws AppBaseException, AppBaseException;

    void createNewDelivery(ExternalTransfer externalTransfer) throws AppBaseException, AppBaseException;

    void updateDelivery(ExternalTransfer externalTransfer) throws AppBaseException, AppBaseException;

    void deleteDeliveryById(Long id) throws AppBaseException, AppBaseException;

}
