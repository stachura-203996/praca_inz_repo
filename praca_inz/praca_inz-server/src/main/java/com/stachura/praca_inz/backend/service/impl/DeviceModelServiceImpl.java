package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.model.Parameter;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceModelRepository;
import com.stachura.praca_inz.backend.service.DeviceModelService;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelListElementDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceModelViewDto;
import com.stachura.praca_inz.backend.web.dto.device.ParameterListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceModelConverter;
import com.stachura.praca_inz.backend.web.dto.converter.ParameterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DeviceModelServiceImpl implements DeviceModelService {

    @Autowired
    private DeviceModelRepository deviceModelRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_READ')")
    public DeviceModelViewDto getDeviceModelViewById(Long id) {
        DeviceModel deviceModel = deviceModelRepository.find(id);
        if (deviceModel.isDeleted()) {
            return null;
        }
        return DeviceModelConverter.toDeviceModelViewDto(deviceModel);
    }

    @Override
    public List<ParameterListElementDto> getDeviceParameters(Long id) {
        DeviceModel deviceModel = deviceModelRepository.find(id);
        Set<Parameter> parameters = deviceModel.getParameters();
        if (deviceModel.isDeleted()) {
            return null;
        }
        List<ParameterListElementDto> parameterListElementDtos = new ArrayList<>();
        for (Parameter a : parameters) {
            parameterListElementDtos.add(ParameterConverter.toParameterListElementDto(a));
        }

        return parameterListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_READ')")
    public List<DeviceModelListElementDto> getAllDeviceModels() {
        List<DeviceModel> deviceModels = deviceModelRepository.findAll();
        List<DeviceModelListElementDto> deviceModelsDto = new ArrayList<>();
        for (DeviceModel a : deviceModels) {
            if (!a.isDeleted()) {
                deviceModelsDto.add(DeviceModelConverter.toDeviceModelListElement(a));
            }
        }
        return deviceModelsDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_CREATE')")
    public void createNewDeviceModel(DeviceModel deviceModel) throws ServiceException {
        try {
            deviceModelRepository.create(deviceModel);
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }

    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_UPDATE')")
    public void updateDeviceModel(DeviceModel deviceModel) throws ServiceException {

        deviceModelRepository.update(deviceModel);

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_DELETE')")
    public void deleteDeviceModelById(Long id) {
        deviceModelRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_DELETE')")
    public void deleteDeviceModel(DeviceModel deviceModel) {
        deviceModelRepository.find(deviceModel.getId()).setDeleted(true);
    }
}
