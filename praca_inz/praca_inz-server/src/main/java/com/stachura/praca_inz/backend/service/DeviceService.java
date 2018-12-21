package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;

import java.util.List;

public interface DeviceService {

    Device getDeviceById(Long id);

//    Device getDeviceByName(String name);

    List<DeviceListElementDto> getAllDevices();

    List<DeviceListElementDto> getAllDevicesForUser();

    void createNewDevice(Device device);

    Device updateDevice(Device device);

    void deleteDeviceById(Long id);

    void deleteDevice(Device device);
}
