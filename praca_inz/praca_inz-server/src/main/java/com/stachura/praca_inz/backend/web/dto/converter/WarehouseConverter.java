package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.web.dto.WarehouseListElementDto;

public class WarehouseConverter {

    public static WarehouseListElementDto toWarehouseUserListElementDto(Warehouse warehouse){
        return WarehouseListElementDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .username(warehouse.getUser().getUsername())
                .devicesNumber(String.valueOf(warehouse.getDevices().size()))
                .build();
    }

    public static WarehouseListElementDto toWarehouseOfficeListElementDto(Warehouse warehouse){
        return WarehouseListElementDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .username(warehouse.getUser().getUsername())
                .officeId(String.valueOf(warehouse.getOffice().getId()))
                .officeName(warehouse.getOffice().getName())
                .devicesNumber(String.valueOf(warehouse.getDevices().size()))
                .build();
    }
}
