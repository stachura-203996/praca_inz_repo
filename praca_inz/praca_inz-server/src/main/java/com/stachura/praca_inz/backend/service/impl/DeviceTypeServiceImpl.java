package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceTypeRepository;
import com.stachura.praca_inz.backend.service.DeviceTypeService;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_TYPE_READ')")
    public DeviceType getDeviceTypeById(Long id) {
        DeviceType deviceType = deviceTypeRepository.find(id);
        if (deviceType.isDeleted()) {
            return null;
        }
        return deviceType;
    }

    /*@Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_TYPE_READ') and hasAuthority('DEPARTMENT_READ')")
    public DeviceType getDeviceTypeByName(String name) {
        return deviceTypeRepository.find(name);
    }*/

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_TYPE_READ')")
    public List<DeviceTypeListElementDto> getAllDeviceTypes() {
        List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
        List<DeviceTypeListElementDto> deviceTypesDto = new ArrayList<>();
        for (DeviceType a : deviceTypes) {
            if (!a.isDeleted()) {
                deviceTypesDto.add(DeviceTypeConverter.toDeviceTypeListElement(a));
            }
        }
        return deviceTypesDto;
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_CREATE')")
    public void createNewDeviceType(DeviceType deviceType) {
        try {

            deviceTypeRepository.create(deviceType);

        } catch (
                EntityException e) {

        }

    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_UPDATE')")
    public DeviceType updateDeviceType(DeviceType deviceType) {
        DeviceType tmp = new DeviceType();
        try {
            if (!deviceTypeRepository.find(deviceType.getId()).isDeleted()) {
                tmp = deviceTypeRepository.update(deviceType);
            }
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_DELETE')")
    public void deleteDeviceTypeById(Long id) {
        deviceTypeRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_TYPE_DELETE')")
    public void deleteDeviceType(DeviceType deviceType) {
        deviceTypeRepository.find(deviceType.getId()).setDeleted(true);
    }
}
