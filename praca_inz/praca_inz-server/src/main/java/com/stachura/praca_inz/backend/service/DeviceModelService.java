package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.web.dto.DeviceModelListElementDto;

import java.util.List;

public interface DeviceModelService {

    DeviceModel getDeviceTypeById(Long id);

//    DeviceModel getDeviceTypeByName(String name);

    List<DeviceModelListElementDto> getAllDeviceTypes();

    void createNewDeviceType(DeviceModel deviceModel);

    DeviceModel updateDeviceType(DeviceModel deviceModel);

    void deleteDeviceTypeById(Long id);

    void deleteDeviceType(DeviceModel deviceModel);
}
