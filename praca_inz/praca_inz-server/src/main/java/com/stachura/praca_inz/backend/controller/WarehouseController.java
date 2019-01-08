package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.service.WarehouseService;
import com.stachura.praca_inz.backend.web.dto.WarehouseListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAll() {
        return warehouseService.getAllOfficeWarehouses();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Warehouse get(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @RequestMapping(value = "/warehouseman",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllWarehousesForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/transfer-request",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForTransferRequest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllForTransferRequest(userRepository.find(auth.getName()).getOffice().getId());
    }

    @RequestMapping(value = "/shipment-request",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForShipmentRequest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return warehouseService.getAllForShipmentRequest(userRepository.find(auth.getName()).getOffice().getId());
    }

    @RequestMapping(value = "/company/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForCompany(@PathVariable Long id) {
        return warehouseService.getAllWarehousesForCompany(id);
    }

    @RequestMapping(value = "/department/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForDepartment(@PathVariable Long id) {
        return warehouseService.getAllwarehousesForDepartment(id);
    }

    @RequestMapping(value = "/office/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<WarehouseListElementDto> getAllWarehousesForOffice(@PathVariable Long id) {
        return warehouseService.getAllWarehousesForOffice(id);
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Warehouse warehouse) {
        try {
            warehouseService.createNewWarehouse(warehouse);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(WarehouseController.class).get(warehouse.getId()));
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Warehouse warehouse) {
        try {
            warehouseService.updateWarehouse(warehouse);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        warehouseService.deleteWarehouseById(id);
    }
}
