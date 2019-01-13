package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.web.dto.device.*;

import java.util.List;

public interface DeviceModelService {

    DeviceModelViewDto getDeviceModelViewById(Long id) throws ServiceException;

    List<ParameterListElementDto> getDeviceModelParameters(Long id) throws ServiceException;

    List<DeviceModelListElementDto> getAllDeviceModels(String username) throws ServiceException;

    Long createNewDeviceModel(DeviceModelAddDto deviceModelAddDto) throws ServiceException;

    void updateDeviceModel(DeviceModelEditDto deviceModelEditDto)throws SecurityException, ServiceException;

    void deleteDeviceModelById(Long id) throws ServiceException;

    DeviceModelEditDto getDeviceModelEdit(Long id) throws ServiceException;

    Long createNewParameter(ParameterListElementDto parameterListElementDto,Long id) throws ServiceException;

    void deleteParameterById(Long id);
}
