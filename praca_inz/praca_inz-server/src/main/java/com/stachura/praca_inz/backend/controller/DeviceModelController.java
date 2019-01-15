package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
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

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/secured/device/model")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AppBaseException.class)
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceModelViewDto get(@PathVariable Long id) throws AppBaseException {
        return deviceModelService.getDeviceModelViewById(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceModelEditDto getToEdit(@PathVariable Long id) throws AppBaseException {
        return deviceModelService.getDeviceModelEdit(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceModelListElementDto> getAll() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceModelService.getAllDeviceModels(auth.getName());
    }

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ParameterListElementDto> getParameters(@PathVariable Long id) throws AppBaseException {
        return deviceModelService.getDeviceModelParameters(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Long create(@RequestBody DeviceModelAddDto deviceModelAddDto) throws AppBaseException {

        Long deviceModelId = null;
        try {
           deviceModelId= deviceModelService.createNewDeviceModel(deviceModelAddDto);
        } catch (RuntimeException e) {
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
                throw new DatabaseErrorException(DatabaseErrorException.DEVICE_MODEL_NAME_NAME_TAKEN);
            }
        }
        return  deviceModelId;
    }

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Long createParameter(@RequestBody ParameterListElementDto parameterListElementDto, @PathVariable Long id) throws AppBaseException {
        Long parameter = null;
        try {
            parameter = deviceModelService.createNewParameter(parameterListElementDto, id);
        } catch (RuntimeException e) {
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
                throw new DatabaseErrorException(DatabaseErrorException.COMPANY_NAME_TAKEN);
            }
        }
        return parameter;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody DeviceModelEditDto deviceModelEditDto) throws AppBaseException {
        deviceModelService.updateDeviceModel(deviceModelEditDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        deviceModelService.deleteDeviceModelById(id);
    }

    @RequestMapping(value = "/parameter/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteParameter(@PathVariable Long id) throws AppBaseException {
        deviceModelService.deleteParameterById(id);
    }
}
