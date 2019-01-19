package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseAddDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseEditDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseViewDto;

public class WarehouseConverter {

    //VIEW
    public static WarehouseViewDto toWarehouseViewDto(Warehouse warehouse) {
        return WarehouseViewDto.builder()
                .name(warehouse.getName())
                .username(warehouse.getUser().getUsername())
                .companyName(warehouse.getOffice().getDepartment().getCompany().getName())
                .departmentName(warehouse.getOffice().getDepartment().getName())
                .userName(warehouse.getUser().getUserdata().getName())
                .userSurname(warehouse.getUser().getUserdata().getSurname())
                .officeName(warehouse.getOffice().getName())
                .devicesNumber(String.valueOf(warehouse.getDevices().size()))
                .build();
    }

    //LIST

    public static WarehouseListElementDto toWarehouseOfficeListElementDto(Warehouse warehouse) {
        return WarehouseListElementDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .login(warehouse.getUser().getUsername())
                .companyName(warehouse.getOffice().getDepartment().getCompany().getName())
                .departmentName(warehouse.getOffice().getDepartment().getName())
                .userName(warehouse.getUser().getUserdata().getName())
                .userSurname(warehouse.getUser().getUserdata().getSurname())
                .officeName(warehouse.getOffice().getName())
                .devicesNumber(String.valueOf(warehouse.getDevices().size()))
                .build();
    }


    //ADD
    public static Warehouse toWarehouse(WarehouseAddDto warehouseAddDto, User user, Office office) {
        Warehouse warehouse = new Warehouse();
        warehouse.setDeleted(false);
        warehouse.setUser(user);
        warehouse.setWarehouseType(WarehouseType.OFFICE);
        warehouse.setOffice(office);
        warehouse.setName(warehouseAddDto.getName());
        return warehouse;
    }

    //TO EDIT DTO
    public static WarehouseEditDto toWarehouseEditDto(Warehouse warehouse) {
        return WarehouseEditDto.builder()
                .name(warehouse.getName())
                .id(warehouse.getId())
                .officeId(warehouse.getOffice().getId())
                .userId(warehouse.getUser().getId())
                .version(warehouse.getVersion())
                .officeName(warehouse.getOffice().getName())
                .selectedUser(warehouse.getUser().getUserdata().getName() + " " + warehouse.getUser().getUserdata().getName() + " | " + warehouse.getUser().getUsername())
                .build();
    }
    
    //SAVE AFTER EDIT
    public static Warehouse toWarehouse(WarehouseEditDto warehouseEditDto, Warehouse beforeWarehouse, User user, Office office) {
        beforeWarehouse.setUser(user);
        beforeWarehouse.setWarehouseType(WarehouseType.OFFICE);
        beforeWarehouse.setOffice(office);
        beforeWarehouse.setName(warehouseEditDto.getName());
        beforeWarehouse.setVersion(warehouseEditDto.getVersion());
        return beforeWarehouse;
    }
}
