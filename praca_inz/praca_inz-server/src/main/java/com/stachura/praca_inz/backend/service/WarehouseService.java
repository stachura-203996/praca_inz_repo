package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseAddDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseEditDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseViewDto;


import java.util.List;

public interface WarehouseService {


    List<WarehouseListElementDto> getAllOfficeWarehouses();

    void createWarehouse(WarehouseAddDto warehouseAddDto)throws ServiceException;

    void updateWarehouse(WarehouseEditDto warehouseEditDto) throws ServiceException;

    void deleteWarehouseById(Long id);



    List<WarehouseListElementDto> getAllWarehousesForCompany(Long id);

    List<WarehouseListElementDto> getAllwarehousesForDepartment(Long id);

    List<WarehouseListElementDto> getAllWarehousesForOffice(Long id);

    List<WarehouseListElementDto> getAllWarehousesForLoggedUser(String username);

    List<WarehouseListElementDto> getAllForTransferRequest(String username);


    WarehouseViewDto getWarehouseToView(Long id);

    WarehouseEditDto getWarehouseToEdit(Long id);
}
