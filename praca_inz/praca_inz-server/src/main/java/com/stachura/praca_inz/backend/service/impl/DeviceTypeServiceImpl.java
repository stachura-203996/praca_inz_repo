package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.repository.DeviceTypeRepository;
import com.stachura.praca_inz.backend.service.DeviceTypeService;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Override
    public List<DeviceTypeListElementDto> getAllDeviceTypes() {
       List<DeviceTypeListElementDto> deviceTypeListElementDtos=new ArrayList<>();
       Lists.newArrayList(deviceTypeRepository.findAll()).stream().forEach(x->deviceTypeListElementDtos.add(DeviceTypeConverter.toDeviceTypeListElement(x)));
       return deviceTypeListElementDtos;
    }

    @Override
    public void createNewDeviceType(String type) {
        DeviceType deviceType=new DeviceType();
        deviceType.setName(type);
        deviceTypeRepository.saveAndFlush(deviceType);
    }

    @Override
    public void deleteDeviceTypeById(Long id) {
        deviceTypeRepository.deleteById(id);
    }
}
