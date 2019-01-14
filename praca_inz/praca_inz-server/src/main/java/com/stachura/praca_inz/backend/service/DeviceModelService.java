package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.web.dto.device.*;

import java.util.List;

public interface DeviceModelService {

    DeviceModelViewDto getDeviceModelViewById(Long id) throws AppBaseException;

    List<ParameterListElementDto> getDeviceModelParameters(Long id) throws AppBaseException;

    List<DeviceModelListElementDto> getAllDeviceModels(String username) throws AppBaseException;

    Long createNewDeviceModel(DeviceModelAddDto deviceModelAddDto) throws AppBaseException;

    void updateDeviceModel(DeviceModelEditDto deviceModelEditDto)throws SecurityException, AppBaseException;

    void deleteDeviceModelById(Long id) throws AppBaseException;

    DeviceModelEditDto getDeviceModelEdit(Long id) throws AppBaseException;

    Long createNewParameter(ParameterListElementDto parameterListElementDto,Long id) throws AppBaseException;

    void deleteParameterById(Long id);
}
