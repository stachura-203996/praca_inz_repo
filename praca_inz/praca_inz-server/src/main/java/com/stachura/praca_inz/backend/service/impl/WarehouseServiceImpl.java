package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import com.stachura.praca_inz.backend.service.WarehouseService;
import com.stachura.praca_inz.backend.web.dto.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.WarehouseConverter;
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
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.find(id);
    }
//
//    @Override
//    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
//    public Warehouse getCompanyById(String name) {
//        return warehouseRepository.find(name);
//    }


    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public List<WarehouseListElementDto> getAllOfficeWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll().stream().filter(x->x.getOffice()!=null).collect(Collectors.toList());
        List<WarehouseListElementDto> warehouseListElementDtos = new ArrayList<>();
        for (Warehouse a : warehouses) {
            warehouseListElementDtos.add(WarehouseConverter.toWarehouseOfficeListElementDto(a));
        }
        return warehouseListElementDtos;
    }

    //TODO
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void createNewWarehouse(Warehouse warehouse) {
        try {
            warehouseRepository.create(warehouse);

        } catch (EntityException e) {

        }

    }

    //TODO
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Warehouse updateWarehouse(Warehouse warehouse) {
        Warehouse tmp = new Warehouse();
        try {
            tmp = warehouseRepository.update(warehouse);
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteWarehouseById(Long id) {
        warehouseRepository.remove(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteWarehouse(Warehouse warehouse) {
        warehouseRepository.remove(warehouse);
    }
}
