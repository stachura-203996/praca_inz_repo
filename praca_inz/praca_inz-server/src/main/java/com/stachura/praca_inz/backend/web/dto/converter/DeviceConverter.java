package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import com.stachura.praca_inz.backend.web.dto.device.DeviceAddDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceEditDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceViewDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeviceConverter {

    //VIEW
    public static DeviceViewDto toDeviceViewDto(Device device) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return DeviceViewDto.builder()
                .id(device.getId())
                .username(device.getWarehouse().getUser().getUsername())
                .serialNumber(device.getSerialNumber())
                .deviceModel(device.getDeviceModel().getName())
                .deviceModelId(device.getDeviceModel().getId())
                .deviceTypeName(device.getDeviceModel().getDeviceType().getName())
                .manufacture(device.getDeviceModel().getManufacture())
                .lastUpdate(formatter.format(device.getLastUpdate().getTime()))
                .status(device.getStatus().name())
                .location(device.getCompany().getName()+
                        " > " + device.getWarehouse().getOffice().getDepartment().getName()+
                        " > " + device.getWarehouse().getOffice().getName()+
                        " > " + device.getWarehouse().getName())
                .userName(device.getWarehouse().getUser().getUserdata().getName())
                .userSurname(device.getWarehouse().getUser().getUserdata().getSurname())
                .build();
    }

    //LIST
    public static DeviceListElementDto toDeviceListElementDto(Device device){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return DeviceListElementDto.builder()
                .id(device.getId())
                .username(device.getWarehouse().getUser().getUsername())
                .serialNumber(device.getSerialNumber())
                .deviceModel(device.getDeviceModel().getName())
                .deviceTypeName(device.getDeviceModel().getDeviceType().getName())
                .manufacture(device.getDeviceModel().getManufacture())
                .lastUpdate(formatter.format(device.getLastUpdate().getTime()))
                .status(device.getStatus().name())
                .location(device.getCompany().getName()+
                        " > " + device.getWarehouse().getOffice().getDepartment().getName()+
                        " > " + device.getWarehouse().getOffice().getName()+
                        " > " + device.getWarehouse().getName())
                .name(device.getWarehouse().getUser().getUserdata().getName())
                .userSurname(device.getWarehouse().getUser().getUserdata().getSurname())
                .build();
    }
    
    //ADD
    public static Device toDevice(DeviceAddDto deviceAddDto, Warehouse warehouse, Company company, DeviceModel deviceModel){
        Device device=new Device();
        device.setStatus(DeviceStatus.REPOSE);
        device.setWarehouse(warehouse);
        device.setDeleted(false);
        device.setCompany(company);
        device.setSerialNumber(deviceAddDto.getSerialNumber());
        device.setLastUpdate(Calendar.getInstance());
        device.setCreateDate(Calendar.getInstance());
        device.setDeviceModel(deviceModel);
        return device;
    }

    //TO EDIT DTO
    public static DeviceEditDto toDeviceEditDto(Device device){
        return DeviceEditDto.builder()
                .id(device.getId())
                .serialNumber(device.getSerialNumber())
                .deviceModelId(device.getDeviceModel().getId())
                .companyId(device.getCompany().getId())
                .warehouseId(device.getWarehouse().getId())
                .companyName(device.getCompany().getName())
                .deviceModelName(device.getDeviceModel().getName())
                .version(device.getVersion())
                .build();
    }

    //SAVE AFTER EDIT
    public static Device toDevice(DeviceEditDto deviceEditDto, Device beforeDevice,Warehouse warehouse, Company company, DeviceModel deviceModel){
        Device device=new Device(beforeDevice.getId(),beforeDevice.getVersion());
        device.setStatus(DeviceStatus.REPOSE);
        device.setDeleted(false);
        device.setCompany(company);
        device.setSerialNumber(deviceEditDto.getSerialNumber());
        device.setLastUpdate(Calendar.getInstance());
        device.setDeviceModel(deviceModel);
        device.setVersion(deviceEditDto.getVersion());
        return device;
    }
}
