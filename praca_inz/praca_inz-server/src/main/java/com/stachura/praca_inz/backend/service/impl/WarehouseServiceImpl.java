package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import com.stachura.praca_inz.backend.service.WarehouseService;
import com.stachura.praca_inz.backend.web.dto.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.WarehouseConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    public Warehouse getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.find(id);
        if (warehouse.isDeleted()) {
            return null;
        }
        return warehouse;
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllOfficeWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll().stream().filter(x -> x.getOffice() != null).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllWarehousesForCompany(Long id) {
        List<Warehouse> warehouses = warehouseRepository.findAll().stream().filter(x -> x.getOffice() != null && x.getOffice().getDepartment().getCompany().getId().equals(id)).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllwarehousesForDepartment(Long id) {
        List<Warehouse> warehouses = warehouseRepository.findAll().stream().filter(x -> x.getOffice() != null && x.getOffice().getDepartment().getId().equals(id)).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllWarehousesForOffice(Long id) {
        List<Warehouse> warehouses = warehouseRepository.findAll().stream().filter(x -> x.getOffice() != null && x.getOffice().getId().equals(id)).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            if (!a.isDeleted()) {
                warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
            }
        }
        return warehouseListElementDtos;
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllWarehousesForLoggedUser(String username) {
        List<Warehouse> warehouses = warehouseRepository.findAll().stream().filter(x -> x.getUser().getUsername().equals(username) &&
                x.getOffice() != null).collect(Collectors.toList());
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
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllForTransferRequest(Long id) {
        List<Warehouse> warehouses = warehouseRepository.findAll().stream().filter(x -> x.getOffice().getId().equals(id)).collect(Collectors.toList());
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
    public void createNewWarehouse(Warehouse warehouse)throws ServiceException {
        try {
            warehouseRepository.create(warehouse);

        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_UPDATE')")
    public void updateWarehouse(Warehouse warehouse) throws ServiceException {
        warehouseRepository.update(warehouse);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_DELETE')")
    public void deleteWarehouseById(Long id) {
        warehouseRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_DELETE')")
    public void deleteWarehouse(Warehouse warehouse) {
        warehouseRepository.find(warehouse.getId()).setDeleted(true);
    }


}
