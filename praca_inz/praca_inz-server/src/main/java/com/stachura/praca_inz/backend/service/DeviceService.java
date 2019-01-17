package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.web.dto.device.*;

import java.util.List;

public interface DeviceService {

    List<DeviceListElementDto> getAllDevices(String username) throws AppBaseException;

    List<DeviceListElementDto> getAllDevicesForCompany(Long id);

    List<DeviceListElementDto> getAllDevicesForDepartment(Long id);

    List<DeviceListElementDto> getAllDevicesForOffice(Long id);

    List<DeviceListElementDto> getAllDevicesForWarehouse(Long id);

    List<DeviceListElementDto> getAllDevicesForLoggedUser(String username);

    void createNewDevice(DeviceAddDto deviceAddDto) throws AppBaseException;

    void updateDevice(DeviceEditDto deviceEditDto) throws  AppBaseException;

    void deleteDeviceById(Long id) throws AppBaseException;

    List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman(String username);

    List<DeviceListElementDto> getAllDevicesForShipmentRequest(String name);

    DeviceViewDto getDeviceToView(Long id)throws AppBaseException;

    DeviceEditDto getDeviceToEdit(Long id)throws AppBaseException;

    List<ParameterListElementDto> getDeviceParameters(Long id) throws AppBaseException;
}
