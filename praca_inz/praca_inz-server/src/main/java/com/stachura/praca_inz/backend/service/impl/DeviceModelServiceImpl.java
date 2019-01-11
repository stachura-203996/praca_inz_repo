package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.model.Parameter;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DeviceModelRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
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
import java.util.stream.Collectors;

@Service
public class DeviceModelServiceImpl implements DeviceModelService {

    @Autowired
    private DeviceModelRepository deviceModelRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_READ')")
    public DeviceModelViewDto getDeviceModelViewById(Long id) throws ServiceException {
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (deviceModel.isDeleted()) {
            return null;
        }
        return DeviceModelConverter.toDeviceModelViewDto(deviceModel);
    }

    @Override
    public List<ParameterListElementDto> getDeviceParameters(Long id) throws ServiceException {
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElseThrow(() -> new ServiceException());
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
    public List<DeviceModelListElementDto> getAllDeviceModels(String username) throws ServiceException {
        List<DeviceModel> deviceModels;
        User user=userRepository.findByUsername(username).orElseThrow(()->new ServiceException());
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            deviceModels = Lists.newArrayList(deviceModelRepository.findAll());
        } else{
            deviceModels = Lists.newArrayList(deviceModelRepository.findAll()).stream().filter(x->x.getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
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
            deviceModelRepository.save(deviceModel);
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_UPDATE')")
    public void updateDeviceModel(DeviceModel deviceModel) throws ServiceException {
        deviceModelRepository.save(deviceModel);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_DELETE')")
    public void deleteDeviceModelById(Long id) throws ServiceException {
        deviceModelRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }

}
