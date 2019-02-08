package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DeviceRepository;
import com.stachura.praca_inz.backend.repository.OfficeRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.repository.WarehouseRepository;
import com.stachura.praca_inz.backend.service.WarehouseService;
import com.stachura.praca_inz.backend.web.dto.converter.WarehouseConverter;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseAddDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseEditDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseViewDto;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    public WarehouseViewDto getWarehouseToView(Long id) throws EntityNotInDatabaseException {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (warehouse.isDeleted()) {
            return null;
        }
        return WarehouseConverter.toWarehouseViewDto(warehouse);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    public WarehouseEditDto getWarehouseToEdit(Long id) throws EntityNotInDatabaseException {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (warehouse.isDeleted()) {
            return null;
        }
        return WarehouseConverter.toWarehouseEditDto(warehouse);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllForTransfer(String username) throws EntityNotInDatabaseException {
        List<Warehouse> warehouses;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (user.getUserRoles().stream().anyMatch(x -> x.getName().equals(Constants.ADMIN_ROLE))) {
            warehouses = Lists.newArrayList(warehouseRepository.findAll());
        } else {
            warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getOffice().getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllOfficeWarehouses(String username) throws EntityNotInDatabaseException {
        List<Warehouse> warehouses;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (user.getUserRoles().stream().anyMatch(x -> x.getName().equals(Constants.ADMIN_ROLE))) {
            warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getWarehouseType().equals(WarehouseType.OFFICE)).collect(Collectors.toList());
        } else {
            warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getWarehouseType().equals(WarehouseType.OFFICE) &&
                    x.getOffice().getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllWarehousesForCompany(Long id) {
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getWarehouseType().equals(WarehouseType.OFFICE) && x.getOffice().getDepartment().getCompany().getId().equals(id)).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllwarehousesForDepartment(Long id) {
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getWarehouseType().equals(WarehouseType.OFFICE) && x.getOffice().getDepartment().getId().equals(id)).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllWarehousesForOffice(Long id) {
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getWarehouseType().equals(WarehouseType.OFFICE) && x.getOffice().getId().equals(id)).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllWarehousesForLoggedUser(String username) {
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getUser().getUsername().equals(username) &&
                x.getOffice() != null && x.getWarehouseType().name().equals(WarehouseType.OFFICE.name())).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseDto = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDevices());
                Hibernate.initialize(a.getOffice());
                warehouseDto.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseDto;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_FOR_TRANSFER_READ')")
    public List<WarehouseListElementDto> getAllForTransferRequest(String username, Long deviceId) throws EntityNotInDatabaseException {
        Long id = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).getOffice().getId();
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getOffice().getId().equals(id) && !x.getId().equals(device.getWarehouse().getId())).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseDto = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDevices());
                Hibernate.initialize(a.getOffice());
                warehouseDto.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_CREATE')")
    public void createWarehouse(WarehouseAddDto warehouseAddDto) throws EntityNotInDatabaseException, DatabaseErrorException {
        try {
            User user = userRepository.findById(warehouseAddDto.getUserId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            Office office = officeRepository.findById(warehouseAddDto.getOfficeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            warehouseRepository.saveAndFlush(WarehouseConverter.toWarehouse(warehouseAddDto, user, office));
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.WAREHOUSE_NAME_TAKEN);
        }

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_UPDATE')")
    public void updateWarehouse(WarehouseEditDto warehouseEditDto) throws EntityNotInDatabaseException, DatabaseErrorException {
        try {
            Warehouse beforeWarehouse = warehouseRepository.findById(warehouseEditDto.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            User user = userRepository.findById(warehouseEditDto.getUserId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            Office office = officeRepository.findById(warehouseEditDto.getOfficeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            warehouseRepository.detach(beforeWarehouse);
            warehouseRepository.saveAndFlush(WarehouseConverter.toWarehouse(warehouseEditDto, beforeWarehouse, user, office));
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.WAREHOUSE_NAME_TAKEN);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_DELETE')")
    public void deleteWarehouseById(Long id) throws EntityNotInDatabaseException {
        warehouseRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

}
