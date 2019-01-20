package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.service.WarehouseService;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseAddDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseEditDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseListElementDto;
import com.stachura.praca_inz.backend.web.dto.warehouse.WarehouseViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/warehouse")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AppBaseException.class)
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    WarehouseViewDto getWarehouseToView(@PathVariable Long id) throws AppBaseException {
        return warehouseService.getWarehouseToView(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    WarehouseEditDto getWarehouseToEdit(@PathVariable Long id) throws AppBaseException {
        return warehouseService.getWarehouseToEdit(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAll() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllOfficeWarehouses(auth.getName());
    }

    @RequestMapping(value = "/warehouseman", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllWarehousesForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForTransfer() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllForTransfer(auth.getName());
    }

    @RequestMapping(value = "/transfer-request/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForTransferRequest(@PathVariable Long id) throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllForTransferRequest(auth.getName(), id);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForCompany(@PathVariable Long id) {
        return warehouseService.getAllWarehousesForCompany(id);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForDepartment(@PathVariable Long id) {
        return warehouseService.getAllwarehousesForDepartment(id);
    }

    @RequestMapping(value = "/office/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForOffice(@PathVariable Long id) {
        return warehouseService.getAllWarehousesForOffice(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody WarehouseAddDto warehouseAddDto) throws AppBaseException {
        warehouseService.createWarehouse(warehouseAddDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody WarehouseEditDto warehouseEditDto) throws AppBaseException {
        warehouseService.updateWarehouse(warehouseEditDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        warehouseService.deleteWarehouseById(id);
    }
}
