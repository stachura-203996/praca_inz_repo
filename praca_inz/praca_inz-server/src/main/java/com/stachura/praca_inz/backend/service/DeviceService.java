package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.web.dto.device.DeviceAddDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceEditDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceViewDto;

import java.util.List;

public interface DeviceService {

    List<DeviceListElementDto> getAllDevices(String username) throws ServiceException;

    List<DeviceListElementDto> getAllDevicesForCompany(Long id);

    List<DeviceListElementDto> getAllDevicesForDepartment(Long id);

    List<DeviceListElementDto> getAllDevicesForOffice(Long id);

    List<DeviceListElementDto> getAllDevicesForWarehouse(Long id);

    List<DeviceListElementDto> getAllDevicesForLoggedUser(String username);

    void createNewDevice(DeviceAddDto deviceAddDto) throws SecurityException, ServiceException;

    void updateDevice(DeviceEditDto deviceEditDto) throws SecurityException, EntityException, ServiceException;

    void deleteDeviceById(Long id) throws ServiceException;

    List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman(String username);

    List<DeviceListElementDto> getAllDevicesForShipmentRequest(String name);

    DeviceViewDto getDeviceToView(Long id)throws ServiceException;

    DeviceEditDto getDeviceToEdit(Long id)throws ServiceException;

    Device getDeviceParameters(Long id);
}
