package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseAddDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseEditDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseViewDto;


import java.util.List;

public interface WarehouseService {


    List<WarehouseListElementDto> getAllOfficeWarehouses(String username) throws SystemBaseException;

    void createWarehouse(WarehouseAddDto warehouseAddDto)throws SystemBaseException;

    void updateWarehouse(WarehouseEditDto warehouseEditDto) throws SystemBaseException;

    void deleteWarehouseById(Long id) throws SystemBaseException;



    List<WarehouseListElementDto> getAllWarehousesForCompany(Long id);

    List<WarehouseListElementDto> getAllwarehousesForDepartment(Long id);

    List<WarehouseListElementDto> getAllWarehousesForOffice(Long id);

    List<WarehouseListElementDto> getAllWarehousesForLoggedUser(String username);

    List<WarehouseListElementDto> getAllForTransferRequest(String username,Long id) throws SystemBaseException;


    WarehouseViewDto getWarehouseToView(Long id) throws SystemBaseException;

    WarehouseEditDto getWarehouseToEdit(Long id) throws SystemBaseException;

    List<WarehouseListElementDto> getAllForTransfer(String name) throws EntityNotInDatabaseException;
}
