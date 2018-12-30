package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceModelRepository;
import com.stachura.praca_inz.backend.service.DeviceModelService;
import com.stachura.praca_inz.backend.web.dto.DeviceModelListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceModelServiceImpl implements DeviceModelService {

    @Autowired
    private DeviceModelRepository deviceModelRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_TYPE_READ')")
    public DeviceModel getDeviceTypeById(Long id) {
        DeviceModel deviceModel = deviceModelRepository.find(id);
        if (deviceModel.isDeleted()) {
            return null;
        }
        return deviceModel;
    }

    /*@Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_TYPE_READ') and hasAuthority('DEPARTMENT_READ')")
    public DeviceModel getDeviceTypeByName(String name) {
        return deviceModelRepository.find(name);
    }*/

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_TYPE_READ')")
    public List<DeviceModelListElementDto> getAllDeviceTypes() {
        List<DeviceModel> deviceModels = deviceModelRepository.findAll();
        List<DeviceModelListElementDto> deviceTypesDto = new ArrayList<>();
        for (DeviceModel a : deviceModels) {
            if (!a.isDeleted()) {
                deviceTypesDto.add(DeviceModelConverter.toDeviceTypeListElement(a));
            }
        }
        return deviceTypesDto;
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_CREATE')")
    public void createNewDeviceType(DeviceModel deviceModel) {
        try {

            deviceModelRepository.create(deviceModel);

        } catch (
                EntityException e) {

        }

    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_UPDATE')")
    public DeviceModel updateDeviceType(DeviceModel deviceModel) {
        DeviceModel tmp = new DeviceModel();
        try {
            if (!deviceModelRepository.find(deviceModel.getId()).isDeleted()) {
                tmp = deviceModelRepository.update(deviceModel);
            }
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_DELETE')")
    public void deleteDeviceTypeById(Long id) {
        deviceModelRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_DELETE')")
    public void deleteDeviceType(DeviceModel deviceModel) {
        deviceModelRepository.find(deviceModel.getId()).setDeleted(true);
    }
}
