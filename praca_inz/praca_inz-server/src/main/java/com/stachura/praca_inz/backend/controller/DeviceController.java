package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.service.DeviceService;
import com.stachura.praca_inz.backend.web.dto.device.*;
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
@RequestMapping("/secured/device")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAll() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceService.getAllDevices(auth.getName());
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForCompany(@PathVariable Long id) {
        return deviceService.getAllDevicesForCompany(id);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForDepartment(@PathVariable Long id) {
        return deviceService.getAllDevicesForDepartment(id);
    }

    @RequestMapping(value = "/office/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForOffice(@PathVariable Long id) {
        return deviceService.getAllDevicesForOffice(id);
    }

    @RequestMapping(value = "/warehouse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForWarehouse(@PathVariable Long id) {
        return deviceService.getAllDevicesForWarehouse(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForLoggedUser() throws EntityNotInDatabaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceService.getAllDevicesForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/warehouseman", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman() throws EntityNotInDatabaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceService.getAllDevicesForLoggedWarehouseman(auth.getName());
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForLoggedUser(@PathVariable String username) throws EntityNotInDatabaseException {
        return deviceService.getAllDevicesForLoggedUser(username);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceViewDto getDeviceToView(@PathVariable Long id) throws SystemBaseException {
        return deviceService.getDeviceToView(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceEditDto getDeviceToEdit(@PathVariable Long id) throws SystemBaseException {
        return deviceService.getDeviceToEdit(id);
    }

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ParameterListElementDto> getParameters(@PathVariable Long id) throws SystemBaseException {
        return deviceService.getDeviceParameters(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody DeviceAddDto deviceAddDto) throws SystemBaseException {
        deviceService.createNewDevice(deviceAddDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody DeviceEditDto deviceEditDto) throws SystemBaseException {
        deviceService.updateDevice(deviceEditDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        deviceService.deleteDeviceById(id);
    }
}
