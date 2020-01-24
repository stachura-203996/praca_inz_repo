package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.web.dto.device.*;

import java.util.List;

public interface DeviceModelService {

    DeviceModelViewDto getDeviceModelViewById(Long id) throws SystemBaseException;

    List<ParameterListElementDto> getDeviceModelParameters(Long id) throws SystemBaseException;

    List<DeviceModelListElementDto> getAllDeviceModels(String username) throws SystemBaseException;

    Long createNewDeviceModel(DeviceModelAddDto deviceModelAddDto) throws SystemBaseException;

    void updateDeviceModel(DeviceModelEditDto deviceModelEditDto)throws SecurityException, SystemBaseException;

    void deleteDeviceModelById(Long id) throws SystemBaseException;

    DeviceModelEditDto getDeviceModelEdit(Long id) throws SystemBaseException;

    Long createNewParameter(ParameterListElementDto parameterListElementDto,Long id) throws SystemBaseException;

    void deleteParameterById(Long id);
}
