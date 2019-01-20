package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseAddDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseEditDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseViewDto;


import java.util.List;

public interface WarehouseService {


    List<WarehouseListElementDto> getAllOfficeWarehouses(String username) throws AppBaseException;

    void createWarehouse(WarehouseAddDto warehouseAddDto)throws AppBaseException;

    void updateWarehouse(WarehouseEditDto warehouseEditDto) throws AppBaseException;

    void deleteWarehouseById(Long id) throws AppBaseException;



    List<WarehouseListElementDto> getAllWarehousesForCompany(Long id);

    List<WarehouseListElementDto> getAllwarehousesForDepartment(Long id);

    List<WarehouseListElementDto> getAllWarehousesForOffice(Long id);

    List<WarehouseListElementDto> getAllWarehousesForLoggedUser(String username);

    List<WarehouseListElementDto> getAllForTransferRequest(String username,Long id) throws AppBaseException;


    WarehouseViewDto getWarehouseToView(Long id) throws AppBaseException;

    WarehouseEditDto getWarehouseToEdit(Long id) throws AppBaseException;

    List<WarehouseListElementDto> getAllForTransfer(String name) throws EntityNotInDatabaseException;
}
