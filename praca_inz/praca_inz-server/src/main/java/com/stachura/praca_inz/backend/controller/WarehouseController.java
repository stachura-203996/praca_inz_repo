package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.service.WarehouseService;
import com.stachura.praca_inz.backend.web.dto.user.UserListElementDto;
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
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    WarehouseViewDto getWarehouseToView(@PathVariable Long id) throws SystemBaseException {
        return warehouseService.getWarehouseToView(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    WarehouseEditDto getWarehouseToEdit(@PathVariable Long id) throws SystemBaseException {
        return warehouseService.getWarehouseToEdit(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getWarehouseUsersToEdit(@PathVariable Long id) throws SystemBaseException {
        return warehouseService.getWarehouseUsersToEdit(id);
    }

    @RequestMapping(value = "/user/{warehouseId}/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void createParameter(@PathVariable Long userId, @PathVariable Long warehouseId) throws SystemBaseException {
        warehouseService.attachNewUserToWarehouse(userId,warehouseId);
    }

    @RequestMapping(value = "/user/{warehouseId}/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteParameter(@PathVariable Long userId, @PathVariable Long warehouseId) throws SystemBaseException {
        warehouseService.detachUserFromWarehouse(userId,warehouseId);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAll() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllOfficeWarehouses(auth.getName());
    }

    @RequestMapping(value = "/warehouseman", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForUser() throws EntityNotInDatabaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllWarehousesForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForTransfer() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllForTransfer(auth.getName());
    }

    @RequestMapping(value = "/transfer-request/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForTransferRequest(@PathVariable Long id) throws SystemBaseException {
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
    public ResponseEntity<?> create(@RequestBody WarehouseAddDto warehouseAddDto) throws SystemBaseException {
        warehouseService.createWarehouse(warehouseAddDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody WarehouseEditDto warehouseEditDto) throws SystemBaseException {
        warehouseService.updateWarehouse(warehouseEditDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        warehouseService.deleteWarehouseById(id);
    }
}
