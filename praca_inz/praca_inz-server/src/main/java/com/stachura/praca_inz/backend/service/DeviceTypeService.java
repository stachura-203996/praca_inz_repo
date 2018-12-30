package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;

import java.util.List;

public interface DeviceTypeService {

    DeviceType getDeviceTypeById(Long id);

//    DeviceType getDeviceTypeByName(String name);

    List<DeviceTypeListElementDto> getAllDeviceTypes();

    void createNewDeviceType(DeviceType deviceType);

    DeviceType updateDeviceType(DeviceType deviceType);

    void deleteDeviceTypeById(Long id);

    void deleteDeviceType(DeviceType deviceType);
}
