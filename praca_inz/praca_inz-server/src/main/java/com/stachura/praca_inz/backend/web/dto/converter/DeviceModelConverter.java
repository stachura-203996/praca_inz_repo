package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelAddDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelEditDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelListElementDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelViewDto;

public class DeviceModelConverter {

    //VIEW
    public static DeviceModelViewDto toDeviceModelViewDto(DeviceModel deviceModel) {
        return DeviceModelViewDto.builder()
                .id(deviceModel.getId())
                .manufacture(deviceModel.getManufacture())
                .name(deviceModel.getName())
                .type(deviceModel.getDeviceType().getName())
                .cost(deviceModel.getCost())
                .numberOfDevices(String.valueOf(deviceModel.getDevices().size()))
                .companyName(deviceModel.getCompany().getName())
                .build();
    }

    //LIST
    public static DeviceModelListElementDto toDeviceModelListElement(DeviceModel deviceModel) {
        return DeviceModelListElementDto.builder()
                .id(deviceModel.getId())
                .name(deviceModel.getName())
                .companyName(deviceModel.getCompany().getName())
                .manufacture(deviceModel.getManufacture())
                .cost(deviceModel.getCost())
                .numberOfDevices(String.valueOf(deviceModel.getDevices().size()))
                .deviceTypeName(deviceModel.getDeviceType().getName())
                .build();

    }

    //ADD
    public static DeviceModel toDeviceModel(DeviceModelAddDto deviceModelAddDto, Company company, DeviceType deviceType) {
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setCompany(company);
        deviceModel.setCost(deviceModelAddDto.getCost());
        deviceModel.setDeleted(false);
        deviceModel.setDeviceType(deviceType);
        deviceModel.setManufacture(deviceModelAddDto.getManufacture());
        deviceModel.setName(deviceModelAddDto.getName());
        return deviceModel;
    }

    //TO EDIT DTO
    public static DeviceModelEditDto toDeviceModelEditDto(DeviceModel deviceModel) {
        return DeviceModelEditDto.builder()
                .id(deviceModel.getId())
                .manufacture(deviceModel.getManufacture())
                .name(deviceModel.getName())
                .type(deviceModel.getDeviceType().getName())
                .cost(deviceModel.getCost())
                .companyId(deviceModel.getCompany().getId())
                .type(deviceModel.getDeviceType().getName())
                .typeId(deviceModel.getDeviceType().getId())
                .companyname(deviceModel.getCompany().getName())
                .build();
    }

    //SAVE AFTER EDIT
    public static DeviceModel toDeviceModel(DeviceModelEditDto deviceModelEditDto, DeviceModel beforeDeviceModel, Company company, DeviceType deviceType) {
        beforeDeviceModel.setCompany(company);
        beforeDeviceModel.setCost(deviceModelEditDto.getCost());
        beforeDeviceModel.setDeleted(false);
        beforeDeviceModel.setDeviceType(deviceType);
        beforeDeviceModel.setManufacture(deviceModelEditDto.getManufacture());
        beforeDeviceModel.setName(deviceModelEditDto.getName());
        beforeDeviceModel.setVersion(deviceModelEditDto.getVersion());
        return beforeDeviceModel;
    }

}
