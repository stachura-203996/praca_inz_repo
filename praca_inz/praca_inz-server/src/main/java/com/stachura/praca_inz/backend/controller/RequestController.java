package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.repository.DeviceModelRepository;
import com.stachura.praca_inz.backend.repository.DeviceRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.repository.WarehouseRepository;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.service.RequestService;
import com.stachura.praca_inz.backend.web.dto.converter.RequestConverter;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/secured/request")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = AppBaseException.class)
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private DeviceModelRepository deviceModelRepository;

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequests(@PathVariable String type) throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequests(type,auth.getName());
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
    List<RequestListElementDto> getAllRequestsForManager() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestForManager(auth.getName());
    }

    @RequestMapping(value = "/other/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequestsFromOtherUsers() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestFromeOtherUsers(auth.getName());
    }

    @RequestMapping(value = "/warehouseman", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllForWarehouseman() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestForWarehouseman(auth.getName());
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllFromOtherWarehouses() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestFromOtherWarehouses(auth.getName());
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
    List<DeviceListElementDto> getAllRequestDevices(@PathVariable Long id) throws AppBaseException {
        return requestService.getAllRequestDevices(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    RequestViewDto getRequestById(@PathVariable Long id) throws AppBaseException {
        Request request=requestService.getRequestById(id);
        Hibernate.initialize(request.getDeviceModel());
        return RequestConverter.toRequestView(request);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createTransferRequest(@RequestBody TransferRequestAddDto transferRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewTransferRequest(transferRequestAddDto,auth.getName());

        } catch (AppBaseException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createDeviceRequest(@RequestBody DeviceRequestAddDto deviceRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewDeviceRequest(deviceRequestAddDto,auth.getName());
        } catch (AppBaseException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeStatus(@RequestBody ChangeStatusDto changeStatusDto) {
        try {
            requestService.realizeRequest(changeStatusDto);
        } catch (AppBaseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/devices/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeStatus(@RequestBody List<Long> devices,@PathVariable Long id) {
        try {
            requestService.addDevicesToRequest(devices,id);
        } catch (AppBaseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Request request) {
        try {
            requestService.updateRequest(request);
        } catch (AppBaseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        requestService.deleteRequestById(id);
    }

}

