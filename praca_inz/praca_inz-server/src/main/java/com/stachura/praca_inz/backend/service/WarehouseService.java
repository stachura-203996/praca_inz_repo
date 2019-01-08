package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.web.dto.WarehouseListElementDto;


import java.util.List;

public interface WarehouseService {

    Warehouse getWarehouseById(Long id);

//    Warehouse getCompanyByName(String name);

    List<WarehouseListElementDto> getAllOfficeWarehouses();

    void createNewWarehouse(Warehouse warehouse) throws ServiceException;

    void updateWarehouse(Warehouse warehouse) throws ServiceException;

    void deleteWarehouseById(Long id);

    void deleteWarehouse(Warehouse warehouse);

    List<WarehouseListElementDto> getAllWarehousesForCompany(Long id);

    List<WarehouseListElementDto> getAllwarehousesForDepartment(Long id);

    List<WarehouseListElementDto> getAllWarehousesForOffice(Long id);

    List<WarehouseListElementDto> getAllWarehousesForLoggedUser(String username);

    List<WarehouseListElementDto> getAllForTransferRequest(Long id);

    List<WarehouseListElementDto> getAllForShipmentRequest(Long id);
}
