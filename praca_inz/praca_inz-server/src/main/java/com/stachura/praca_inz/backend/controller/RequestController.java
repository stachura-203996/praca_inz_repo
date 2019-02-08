package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.service.RequestService;
import com.stachura.praca_inz.backend.web.dto.converter.RequestConverter;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.*;
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
@RequestMapping("/secured/request")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequests(@PathVariable String type) throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequests(type, auth.getName());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequestsForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestsForUser(auth.getName());
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequestsForManager() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestForManager(auth.getName());
    }

    @RequestMapping(value = "/other/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequestsFromOtherUsers() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestFromeOtherUsers(auth.getName());
    }

    @RequestMapping(value = "/warehouseman", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllForWarehouseman() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestForWarehouseman(auth.getName());
    }

    @RequestMapping(value = "/office/{type}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllNotificationsForOffice(@PathVariable Long id, @PathVariable String type) {
        return requestService.getAllRequestsForOffice(type, id);
    }

    @RequestMapping(value = "/devices/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllRequestDevices(@PathVariable Long id) throws SystemBaseException {
        return requestService.getAllRequestDevices(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    RequestViewDto getRequestById(@PathVariable Long id) throws SystemBaseException {
        Request request = requestService.getRequestById(id);
        return RequestConverter.toRequestView(request);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createTransferRequest(@RequestBody TransferRequestAddDto transferRequestAddDto) throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        requestService.createNewTransferRequest(transferRequestAddDto, auth.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createDeviceRequest(@RequestBody DeviceRequestAddDto deviceRequestAddDto) throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        requestService.createNewDeviceRequest(deviceRequestAddDto, auth.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeStatus(@RequestBody ChangeStatusDto changeStatusDto) throws SystemBaseException {
        requestService.realizeRequest(changeStatusDto);
    }

    @RequestMapping(value = "/devices/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addDevicesToRequest(@RequestBody List<Long> devices, @PathVariable Long id) throws SystemBaseException {
        requestService.addDevicesToRequest(devices, id);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Request request) throws SystemBaseException {
        requestService.updateRequest(request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        requestService.deleteRequestById(id);
    }

}

