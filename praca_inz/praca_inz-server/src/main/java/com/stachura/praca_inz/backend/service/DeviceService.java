package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.web.dto.device.*;

import java.util.List;

public interface DeviceService {

    List<DeviceListElementDto> getAllDevices(String username) throws SystemBaseException;

    List<DeviceListElementDto> getAllDevicesForCompany(Long id);

    List<DeviceListElementDto> getAllDevicesForDepartment(Long id);

    List<DeviceListElementDto> getAllDevicesForOffice(Long id);

    List<DeviceListElementDto> getAllDevicesForWarehouse(Long id);

    List<DeviceListElementDto> getAllDevicesForLoggedUser(String username) throws EntityNotInDatabaseException;

    void createNewDevice(DeviceAddDto deviceAddDto) throws SystemBaseException;

    void updateDevice(DeviceEditDto deviceEditDto) throws SystemBaseException;

    void deleteDeviceById(Long id) throws SystemBaseException;

    List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman(String username) throws EntityNotInDatabaseException;

    DeviceViewDto getDeviceToView(Long id)throws SystemBaseException;

    DeviceEditDto getDeviceToEdit(Long id)throws SystemBaseException;

    List<ParameterListElementDto> getDeviceParameters(Long id) throws SystemBaseException;
}
