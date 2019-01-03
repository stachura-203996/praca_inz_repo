package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelListElementDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelViewDto;
import com.stachura.praca_inz.backend.web.dto.device.ParameterListElementDto;

import java.util.List;

public interface DeviceModelService {

    DeviceModelViewDto getDeviceModelViewById(Long id);



    List<ParameterListElementDto> getDeviceParameters(Long id);

    List<DeviceModelListElementDto> getAllDeviceModels();

    void createNewDeviceModel(DeviceModel deviceModel) throws ServiceException;

    void updateDeviceModel(DeviceModel deviceModel)throws SecurityException, ServiceException;

    void deleteDeviceModelById(Long id);

    void deleteDeviceModel(DeviceModel deviceModel);
}
