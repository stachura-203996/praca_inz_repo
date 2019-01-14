package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.service.DeviceTypeService;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/device/type")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = AppBaseException.class)
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceTypeListElementDto> getAll() {
        return deviceTypeService.getAllDeviceTypes();
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody String type) throws AppBaseException {
        try {
            deviceTypeService.createNewDeviceType(type);
        } catch (AppBaseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        deviceTypeService.deleteDeviceTypeById(id);
    }
}
