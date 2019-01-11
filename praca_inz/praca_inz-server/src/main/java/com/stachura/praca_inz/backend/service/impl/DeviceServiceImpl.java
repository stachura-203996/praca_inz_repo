package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.*;
import com.stachura.praca_inz.backend.service.DeviceService;
import com.stachura.praca_inz.backend.web.dto.device.DeviceAddDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceEditDto;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceConverter;
import com.stachura.praca_inz.backend.web.dto.device.DeviceViewDto;
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

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DeviceModelRepository deviceModelRepository;



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
    public void createNewDevice(DeviceAddDto deviceAddDto) throws ServiceException {
        Warehouse warehouse=warehouseRepository.findById(deviceAddDto.getWarehouseId()).orElseThrow(()->new ServiceException());
        Company company=companyRepository.findById(deviceAddDto.getCompanyId()).orElseThrow(()->new ServiceException());
        DeviceModel deviceModel=deviceModelRepository.findById(deviceAddDto.getDeviceModelId()).orElseThrow(()->new ServiceException());
            deviceRepository.save(DeviceConverter.toDevice(deviceAddDto,warehouse,company,deviceModel));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_UPDATE')")
    public void updateDevice(DeviceEditDto deviceEditDto) throws ServiceException {
        Warehouse warehouse=warehouseRepository.findById(deviceEditDto.getWarehouseId()).orElseThrow(()->new ServiceException());
        Company company=companyRepository.findById(deviceEditDto.getCompanyId()).orElseThrow(()->new ServiceException());
        DeviceModel deviceModel=deviceModelRepository.findById(deviceEditDto.getDeviceModelId()).orElseThrow(()->new ServiceException());
        Device device=deviceRepository.findById(deviceEditDto.getId()).orElseThrow(()->new ServiceException());
        deviceRepository.save(DeviceConverter.toDevice(deviceEditDto,device,warehouse,company,deviceModel));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_DELETE')")
    public void deleteDeviceById(Long id) throws ServiceException {
        deviceRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }




    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_READ')")
    public DeviceViewDto getDeviceToView(Long id) throws ServiceException {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (device.isDeleted()) {
            return null;
        }
        return DeviceConverter.toDeviceViewDto(device);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_READ')")
    public DeviceEditDto getDeviceToEdit(Long id) throws ServiceException {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (device.isDeleted()) {
            return null;
        }
        return DeviceConverter.toDeviceEditDto(device);
    }

    @Override
    public Device getDeviceParameters(Long id) {
        return null;
    }

}
