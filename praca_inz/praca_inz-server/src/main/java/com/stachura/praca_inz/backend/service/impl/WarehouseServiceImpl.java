package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.OfficeRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.repository.WarehouseRepository;
import com.stachura.praca_inz.backend.service.WarehouseService;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseAddDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseEditDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.WarehouseConverter;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseViewDto;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficeRepository officeRepository;


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    public WarehouseViewDto getWarehouseToView(Long id) throws ServiceException {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (warehouse.isDeleted()) {
            return null;
        }
        return WarehouseConverter.toWarehouseViewDto(warehouse);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    public WarehouseEditDto getWarehouseToEdit(Long id) throws ServiceException {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (warehouse.isDeleted()) {
            return null;
        }
        return WarehouseConverter.toWarehouseEditDto(warehouse);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllOfficeWarehouses() {
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getOffice() != null).collect(Collectors.toList());
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
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getOffice() != null && x.getOffice().getDepartment().getCompany().getId().equals(id)).collect(Collectors.toList());
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
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getOffice() != null && x.getOffice().getDepartment().getId().equals(id)).collect(Collectors.toList());
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
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getOffice() != null && x.getOffice().getId().equals(id)).collect(Collectors.toList());
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
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<WarehouseListElementDto> getAllForTransferRequest(String username) throws ServiceException {
        Long id =userRepository.findByUsername(username).orElseThrow(() -> new ServiceException()).getOffice().getId();
        List<Warehouse> warehouses = Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x -> x.getOffice().getId().equals(id)).collect(Collectors.toList());
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
    public void createWarehouse(WarehouseAddDto warehouseAddDto)throws ServiceException {

            User user=userRepository.findById(warehouseAddDto.getUserId()).orElseThrow(() -> new ServiceException());
            Office office=officeRepository.findById(warehouseAddDto.getOfficeId()).orElseThrow(() -> new ServiceException());
            warehouseRepository.save(WarehouseConverter.toWarehouse(warehouseAddDto,user,office));


    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_UPDATE')")
    public void updateWarehouse(WarehouseEditDto warehouseEditDto) throws ServiceException {
        Warehouse beforeWarehouse=warehouseRepository.findById(warehouseEditDto.getId()).orElseThrow(() -> new ServiceException());
        User user=userRepository.findById(warehouseEditDto.getUserId()).orElseThrow(() -> new ServiceException());
        Office office=officeRepository.findById(warehouseEditDto.getOfficeId()).orElseThrow(() -> new ServiceException());
        warehouseRepository.save(WarehouseConverter.toWarehouse(warehouseEditDto,beforeWarehouse,user,office));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_DELETE')")
    public void deleteWarehouseById(Long id) throws ServiceException {
        warehouseRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }

}
