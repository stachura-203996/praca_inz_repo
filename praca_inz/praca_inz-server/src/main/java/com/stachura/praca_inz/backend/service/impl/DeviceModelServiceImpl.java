package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.model.Parameter;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.*;
import com.stachura.praca_inz.backend.service.DeviceModelService;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceModelConverter;
import com.stachura.praca_inz.backend.web.dto.converter.ParameterConverter;
import com.stachura.praca_inz.backend.web.dto.device.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
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
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_VIEW_READ')")
    public DeviceModelViewDto getDeviceModelViewById(Long id) throws EntityNotInDatabaseException {
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (deviceModel.isDeleted()) {
            return null;
        }
        return DeviceModelConverter.toDeviceModelViewDto(deviceModel);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_EDIT_READ')")
    public DeviceModelEditDto getDeviceModelEdit(Long id) throws EntityNotInDatabaseException {
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (deviceModel.isDeleted()) {
            return null;
        }
        return DeviceModelConverter.toDeviceModelEditDto(deviceModel);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PARAMETER_CREATE')")
    public Long createNewParameter(ParameterListElementDto parameterListElementDto, Long id) throws EntityNotInDatabaseException {
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        parameterRepository.saveAndFlush(ParameterConverter.toParameter(parameterListElementDto, deviceModel));
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PARAMETER_LIST_READ')")
    public List<ParameterListElementDto> getDeviceModelParameters(Long id) throws EntityNotInDatabaseException {
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
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
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_LIST_READ')")
    public List<DeviceModelListElementDto> getAllDeviceModels(String username) throws EntityNotInDatabaseException {
        List<DeviceModel> deviceModels;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (user.getUserRoles().stream().anyMatch(x -> x.getName().equals(Constants.ADMIN_ROLE))) {
            deviceModels = Lists.newArrayList(deviceModelRepository.findAll());
        } else {
            deviceModels = Lists.newArrayList(deviceModelRepository.findAll()).stream().filter(x -> x.getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
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
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_CREATE')")
    public Long createNewDeviceModel(DeviceModelAddDto deviceModelAddDto) throws EntityNotInDatabaseException, DatabaseErrorException {
        DeviceType deviceType = deviceTypeRepository.findById(deviceModelAddDto.getTypeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Company company = companyRepository.findById(deviceModelAddDto.getCompanyId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        DeviceModel deviceModel = DeviceModelConverter.toDeviceModel(deviceModelAddDto, company, deviceType);
        try {
            deviceModelRepository.saveAndFlush(deviceModel);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.DEVICE_MODEL_NAME_NAME_TAKEN);
        }
        return deviceModel.getId();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_UPDATE')")
    public void updateDeviceModel(DeviceModelEditDto deviceModelEditDto) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException {
        try {
            DeviceType deviceType = deviceTypeRepository.findById(deviceModelEditDto.getTypeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            Company company = companyRepository.findById(deviceModelEditDto.getCompanyId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            DeviceModel deviceModel = deviceModelRepository.findById(deviceModelEditDto.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            deviceModelRepository.detach(deviceModel);
            deviceModelRepository.saveAndFlush(DeviceModelConverter.toDeviceModel(deviceModelEditDto, deviceModel, company, deviceType));
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.DEVICE_MODEL_NAME_NAME_TAKEN);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEVICE_MODEL_DELETE')")
    public void deleteDeviceModelById(Long id) throws EntityNotInDatabaseException {
        deviceModelRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PARAMETER_DELETE')")
    public void deleteParameterById(Long id) {
        parameterRepository.deleteById(id);
    }


}
