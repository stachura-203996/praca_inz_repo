package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceModelRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import com.stachura.praca_inz.backend.service.RequestService;
import com.stachura.praca_inz.backend.web.dto.converter.RequestConverter;
import com.stachura.praca_inz.backend.web.dto.request.*;
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
@RequestMapping("/secured/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private DeviceModelRepository deviceModelRepository;

    @RequestMapping(value = "/type/{type}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequestsForUser(@PathVariable String type) {
        return requestService.getAllRequests(type);
    }

    @RequestMapping(value = "/user/{type}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllNotificationsForUser(@PathVariable String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestsForUser(type,auth.getName());
    }

    @RequestMapping(value = "/office/{type}/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllNotificationsForOffice(@PathVariable Long id,@PathVariable String type) {
        return requestService.getAllRequestsForOffice(type,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Request request) {
        try {
            requestService.createNewRequest(request);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(RequestController.class).getRequestById(request.getId()));
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/transfer",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createTransferRequest(@RequestBody TransferRequestAddDto transferRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewRequest(RequestConverter.toRequest(transferRequestAddDto,deviceRepository,warehouseRepository,userRepository.find(auth.getName())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(RequestController.class).getRequestById(request.getId()));
//        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/device",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createDeviceRequest(@RequestBody DeviceRequestAddDto deviceRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewRequest(RequestConverter.toRequest(deviceRequestAddDto,deviceModelRepository,warehouseRepository,userRepository.find(auth.getName())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(RequestController.class).getRequestById(request.getId()));
//        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delivery",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createDeliveryRequest(@RequestBody DeliveryRequestAddDto deliveryRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewRequest(RequestConverter.toRequest(deliveryRequestAddDto,deviceModelRepository,warehouseRepository,userRepository.find(auth.getName())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(RequestController.class).getRequestById(request.getId()));
//        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/shipment",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createShipmentRequest(@RequestBody ShipmentRequestAddDto shipmentRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewRequest(RequestConverter.toRequest(shipmentRequestAddDto,deviceRepository,warehouseRepository,userRepository.find(auth.getName())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(RequestController.class).getRequestById(request.getId()));
//        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Request request) {
        try {
            requestService.updateRequest(request);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        requestService.deleteRequestById(id);
    }
}
