package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DeviceRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.DeviceService;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_READ')")
    public Device getDeviceById(Long id) throws ServiceException {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (device.isDeleted()) {
            return null;
        }
        return device;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevices(String username) throws ServiceException {
        List<Device> devices;
        User user=userRepository.findByUsername(username).orElseThrow(()->new ServiceException());
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            devices = Lists.newArrayList(deviceRepository.findAll());
        } else{
            devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x->x.getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                Hibernate.initialize(a.getWarehouse());
                Hibernate.initialize(a.getCompany());
                Hibernate.initialize(a.getDeviceModel().getDeviceType());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForCompany(Long id) {
        List<Device> devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x -> {
            Office office = Optional.ofNullable(x.getWarehouse().getOffice())
                    .orElseGet(() -> x.getWarehouse().getUser().getOffice());
            return office.getDepartment().getCompany().getId().equals(id);
        }).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForDepartment(Long id) {
        List<Device> devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x ->
        {
            Office office = Optional.ofNullable(x.getWarehouse().getOffice())
                    .orElseGet(() -> x.getWarehouse().getUser().getOffice());
            return office.getDepartment().getId().equals(id);
        }).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForOffice(Long id) {
        List<Device> devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x -> x.getWarehouse().getOffice().getId().equals(id)).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForWarehouse(Long id) {
        List<Device> devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x -> x.getWarehouse().getId().equals(id)).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForLoggedUser(String username) {
        List<Device> devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x -> x.getWarehouse().getUser().getUsername().equals(username) &&
                x.getWarehouse().getWarehouseType().name().equals(WarehouseType.USER.name())).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                Hibernate.initialize(a.getDeviceModel().getName());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_CREATE')")
    public void createNewDevice(Device device) throws ServiceException {
            deviceRepository.save(device);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_UPDATE')")
    public void updateDevice(Device device) throws ServiceException {
        deviceRepository.save(device);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_DELETE')")
    public void deleteDeviceById(Long id) throws ServiceException {
        deviceRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman(String username) {
        List<Device> devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x -> x.getWarehouse().getUser().getUsername().equals(username) &&
                x.getWarehouse().getWarehouseType().name().equals(WarehouseType.OFFICE.name())).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                Hibernate.initialize(a.getDeviceModel().getName());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForShipmentRequest(String name) {
        List<Device> devices = Lists.newArrayList(deviceRepository.findAll()).stream().filter(x -> x.getWarehouse().getUser().getUsername().equals(name) &&
                x.getWarehouse().getWarehouseType().name().equals(WarehouseType.OFFICE.name()) && x.getStatus().name().equals(DeviceStatus.REPOSE.name())).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDeviceModel());
                Hibernate.initialize(a.getDeviceModel().getName());
                devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
            }
        }
        return devicesDto;
    }

}
