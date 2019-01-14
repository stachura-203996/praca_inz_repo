package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.AppBaseException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.service.DeviceModelService;
import com.stachura.praca_inz.backend.web.dto.device.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/device/model")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = AppBaseException.class)
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;



    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceModelViewDto get(@PathVariable Long id) throws ServiceException {
        return deviceModelService.getDeviceModelViewById(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    DeviceModelEditDto getToEdit(@PathVariable Long id) throws ServiceException {
        return deviceModelService.getDeviceModelEdit(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceModelListElementDto> getAll() throws ServiceException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceModelService.getAllDeviceModels(auth.getName());
    }

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ParameterListElementDto> getParameters(@PathVariable Long id) throws ServiceException {
        return deviceModelService.getDeviceModelParameters(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Long create(@RequestBody DeviceModelAddDto deviceModelAddDto) throws ServiceException {
        return  deviceModelService.createNewDeviceModel(deviceModelAddDto);
    }

    @RequestMapping(value = "/parameters/{id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Long createParameter(@RequestBody ParameterListElementDto parameterListElementDto,@PathVariable Long id) throws ServiceException {
        return  deviceModelService.createNewParameter(parameterListElementDto,id);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody DeviceModelEditDto deviceModelEditDto) throws ServiceException {
            deviceModelService.updateDeviceModel(deviceModelEditDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws ServiceException {
        deviceModelService.deleteDeviceModelById(id);
    }

    @RequestMapping(value = "/parameter/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteParameter(@PathVariable Long id) throws ServiceException {
        deviceModelService.deleteParameterById(id);
    }
}
