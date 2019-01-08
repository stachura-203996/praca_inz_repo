package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
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

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_READ')")
    public Device getDeviceById(Long id) {
        Device device = deviceRepository.find(id);
        if (device.isDeleted()) {
            return null;
        }
        return device;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
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
        List<Device> devices = deviceRepository.findAll().stream().filter(x -> {
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
        List<Device> devices = deviceRepository.findAll().stream().filter(x ->
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
        List<Device> devices = deviceRepository.findAll().stream().filter(x -> x.getWarehouse().getOffice().getId().equals(id)).collect(Collectors.toList());
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
        List<Device> devices = deviceRepository.findAll().stream().filter(x -> x.getWarehouse().getId().equals(id)).collect(Collectors.toList());
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
        List<Device> devices = deviceRepository.findAll().stream().filter(x -> x.getWarehouse().getUser().getUsername().equals(username) &&
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
        try {
            deviceRepository.create(device);
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_UPDATE')")
    public void updateDevice(Device device) throws ServiceException {
        deviceRepository.update(device);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_DELETE')")
    public void deleteDeviceById(Long id) {
        deviceRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_DELETE')")
    public void deleteDevice(Device device) {
        deviceRepository.find(device.getId()).setDeleted(true);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman(String username) {
        List<Device> devices = deviceRepository.findAll().stream().filter(x -> x.getWarehouse().getUser().getUsername().equals(username) &&
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
        List<Device> devices = deviceRepository.findAll().stream().filter(x -> x.getWarehouse().getUser().getUsername().equals(name) &&
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
