package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.service.DeviceModelService;
import com.stachura.praca_inz.backend.web.dto.device.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/device/model")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;




    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceModelViewDto get(@PathVariable Long id) throws SystemBaseException {
        return deviceModelService.getDeviceModelViewById(id);
    }

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Long createParameter(@RequestBody ParameterListElementDto parameterListElementDto, @PathVariable Long id) throws SystemBaseException {
        return  deviceModelService.createNewParameter(parameterListElementDto, id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceModelEditDto getToEdit(@PathVariable Long id) throws SystemBaseException {
        return deviceModelService.getDeviceModelEdit(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceModelListElementDto> getAll() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceModelService.getAllDeviceModels(auth.getName());
    }

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ParameterListElementDto> getParameters(@PathVariable Long id) throws SystemBaseException {
        return deviceModelService.getDeviceModelParameters(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Long create(@RequestBody DeviceModelAddDto deviceModelAddDto) throws SystemBaseException {
        return  deviceModelService.createNewDeviceModel(deviceModelAddDto);
    }



    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody DeviceModelEditDto deviceModelEditDto) throws SystemBaseException {
        deviceModelService.updateDeviceModel(deviceModelEditDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        deviceModelService.deleteDeviceModelById(id);
    }

    @RequestMapping(value = "/parameter/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteParameter(@PathVariable Long id) throws SystemBaseException {
        deviceModelService.deleteParameterById(id);
    }
}
