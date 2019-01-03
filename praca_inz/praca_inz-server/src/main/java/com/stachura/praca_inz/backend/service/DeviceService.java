package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;

import java.util.List;

public interface DeviceService {

    Device getDeviceById(Long id);

//    Device getDeviceByName(String name);

    List<DeviceListElementDto> getAllDevices();

    List<DeviceListElementDto> getAllDevicesForCompany(Long id);

    List<DeviceListElementDto> getAllDevicesForDepartment(Long id);

    List<DeviceListElementDto> getAllDevicesForOffice(Long id);

    List<DeviceListElementDto> getAllDevicesForWarehouse(Long id);

    List<DeviceListElementDto> getAllDevicesForLoggedUser(String username);

    void createNewDevice(Device device) throws SecurityException, ServiceException;

    void updateDevice(Device device) throws SecurityException, EntityException, ServiceException;

    void deleteDeviceById(Long id);

    void deleteDevice(Device device);

    List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman(String username);

    List<DeviceListElementDto> getAllDevicesForRequest(String name);


}
