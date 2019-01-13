package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.*;
import com.stachura.praca_inz.backend.service.DeviceModelService;
import com.stachura.praca_inz.backend.web.dto.device.*;
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

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private ParameterRepository parameterRepository;

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
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_READ')")
    public DeviceModelEditDto getDeviceModelEdit(Long id) throws ServiceException {
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (deviceModel.isDeleted()) {
            return null;
        }
        return DeviceModelConverter.toDeviceModelEditDto(deviceModel);
    }

    @Override
    public Long createNewParameter(ParameterListElementDto parameterListElementDto,Long id) throws ServiceException {
        DeviceModel deviceModel=deviceModelRepository.findById(id).orElseThrow(()->new ServiceException());
        parameterRepository.save(ParameterConverter.toParameter(parameterListElementDto,deviceModel));
        return null;
    }



    @Override
    public List<ParameterListElementDto> getDeviceModelParameters(Long id) throws ServiceException {
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
    public Long createNewDeviceModel(DeviceModelAddDto deviceModelAddDto) throws ServiceException {
        DeviceType deviceType=deviceTypeRepository.findById(deviceModelAddDto.getTypeId()).orElseThrow(()->new ServiceException());
        Company company=companyRepository.findById(deviceModelAddDto.getCompanyId()).orElseThrow(()->new ServiceException());
         DeviceModel deviceModel=DeviceModelConverter.toDeviceModel(deviceModelAddDto,company,deviceType);
        deviceModelRepository.save(deviceModel);
        return  deviceModel.getId();
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_UPDATE')")
    public void updateDeviceModel(DeviceModelEditDto deviceModelEditDto) throws ServiceException {
        DeviceType deviceType=deviceTypeRepository.findById(deviceModelEditDto.getTypeId()).orElseThrow(()->new ServiceException());
        Company company=companyRepository.findById(deviceModelEditDto.getCompanyId()).orElseThrow(()->new ServiceException());
        DeviceModel deviceModel=deviceModelRepository.findById(deviceModelEditDto.getId()).orElseThrow(()->new ServiceException());
        deviceModelRepository.save(DeviceModelConverter.toDeviceModel(deviceModelEditDto,deviceModel,company,deviceType));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_MODEL_DELETE')")
    public void deleteDeviceModelById(Long id) throws ServiceException {
        deviceModelRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }


    @Override
    public void deleteParameterById(Long id) {
        parameterRepository.deleteById(id);
    }


}
