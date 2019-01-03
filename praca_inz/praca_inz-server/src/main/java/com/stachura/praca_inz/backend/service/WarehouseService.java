package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.WarehouseListElementDto;


import java.util.List;

public interface WarehouseService {

    Warehouse getWarehouseById(Long id);

//    Warehouse getCompanyByName(String name);

    List<WarehouseListElementDto> getAllOfficeWarehouses();

    void createNewWarehouse(Warehouse warehouse);

    Warehouse updateWarehouse(Warehouse warehouse);

    void deleteWarehouseById(Long id);

    void deleteWarehouse(Warehouse warehouse);

    List<WarehouseListElementDto> getAllWarehousesForCompany(Long id);

    List<WarehouseListElementDto> getAllwarehousesForDepartment(Long id);

    List<WarehouseListElementDto> getAllWarehousesForOffice(Long id);

    List<WarehouseListElementDto> getAllWarehousesForLoggedUser(String username);
}
