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
                .companyName(warehouse.getOffice().getDepartment().getCompany().getName())
                .departmentName(warehouse.getOffice().getDepartment().getName())
                .responsibleFor(getResponsibleFor(warehouse))
                .officeName(warehouse.getOffice().getName())
                .devicesNumber(String.valueOf(warehouse.getDevices().size()))
                .build();
    }

    //LIST

    public static WarehouseListElementDto toWarehouseOfficeListElementDto(Warehouse warehouse) {
        return WarehouseListElementDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .companyName(warehouse.getOffice().getDepartment().getCompany().getName())
                .departmentName(warehouse.getOffice().getDepartment().getName())
                .responsibleFor(getResponsibleFor(warehouse))
                .officeName(warehouse.getOffice().getName())
                .devicesNumber(String.valueOf(warehouse.getDevices().size()))
                .build();
    }

    //ADD
    public static Warehouse toWarehouse(WarehouseAddDto warehouseAddDto, User user, Office office) {
        Warehouse warehouse = new Warehouse();
        warehouse.setDeleted(false);
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
        beforeWarehouse.setWarehouseType(WarehouseType.OFFICE);
        beforeWarehouse.setOffice(office);
        beforeWarehouse.setName(warehouseEditDto.getName());
        beforeWarehouse.setVersion(warehouseEditDto.getVersion());
        return beforeWarehouse;
    }

    public static String getResponsibleFor(Warehouse warehouse){
        return warehouse.getUser().getUserdata().getName()+" "+warehouse.getUser().getUserdata().getSurname()+" | "+warehouse.getUser().getUsername();
    }
}

