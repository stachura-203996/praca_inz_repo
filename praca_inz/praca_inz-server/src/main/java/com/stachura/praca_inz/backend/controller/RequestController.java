package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceModelRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.repository.interfaces.WarehouseRepository;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.service.RequestService;
import com.stachura.praca_inz.backend.web.dto.converter.RequestConverter;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private NotificationService notificationService;

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
    List<RequestListElementDto> getAllRequestsForUser(@PathVariable String type) {
        return requestService.getAllRequests(type);
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
    List<RequestListElementDto> getAllRequestsForManager() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestForManager(auth.getName());
    }

    @RequestMapping(value = "/other/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllRequestsFromOtherUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestFromeOtherUsers(auth.getName());
    }

    @RequestMapping(value = "/warehouseman", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllForWarehouseman() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requestService.getAllRequestForWarehouseman(auth.getName());
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<RequestListElementDto> getAllFromOtherWarehouses() {
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
    List<DeviceListElementDto> getAllRequestDevices(@PathVariable Long id) {
        return requestService.getAllRequestDevices(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    RequestViewDto getRequestById(@PathVariable Long id) {
        Request request=requestService.getRequestById(id);
        Hibernate.initialize(request.getDeviceModel());
        return RequestConverter.toRequestView(request);
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

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createTransferRequest(@RequestBody TransferRequestAddDto transferRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Request request = RequestConverter.toRequest(transferRequestAddDto, deviceRepository, warehouseRepository, userRepository.find(auth.getName()));
            requestService.createNewRequest(request);

            List<User> userList=userRepository.findAll().stream().filter(x->x.getOffice().getId().equals(request.getUser().getOffice().getId())
                    && x.getUserRoles().stream().anyMatch(z->z.getName().equals("MANAGER"))).collect(Collectors.toList());

            for (User u :userList) {
                notificationService.createNewNotification(this.getRequestSentNotifiaction(request,u));
                notificationService.createNewNotification(this.getRequestReceivedManagerNotifiaction(request,u));
            }
        } catch (ServiceException e) {
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
            requestService.createNewRequest(RequestConverter.toRequest(deviceRequestAddDto, deviceModelRepository, warehouseRepository, userRepository.find(auth.getName())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createDeliveryRequest(@RequestBody DeliveryRequestAddDto deliveryRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewRequest(RequestConverter.toRequest(deliveryRequestAddDto, deviceModelRepository, warehouseRepository, userRepository.find(auth.getName())));
        } catch (ServiceException e) {
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
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/devices/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeStatus(@RequestBody List<Long> devices,@PathVariable Long id) {
        try {
            requestService.addDevicesToRequest(devices,id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
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


    String getRequestUrl(Request request) {
        String url=null;
        switch (request.getRequestType()) {
            case DEVICE_REQUEST:
                url="/page/devices/request/view/" + request.getId();
                break;
            case TRANSFER_REQUEST:
                url="/page/devices/transfer/request/view/"+request.getId();
                break;
            case DELIVERY_REQUEST:
                url="/page/warehouses/delivery/request/view/" + request.getId();
                break;

            case SHIPMENT_REQUEST:
                url= "/page/warehouses/shipment/request/view/" + request.getId();
                break;
        }
        return url;
    }

    Notification getRequestSentNotifiaction(Request request,User reciever) {
        Notification notification = new Notification();
        notification.setUrl(getRequestUrl(request));
        notification.setUser(request.getUser());
        notification.setReaded(false);
        notification.setTitle("Request sent");
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setDescription("Your request was sent to:" + reciever.getUserdata().getName()+" "+reciever.getUserdata().getSurname()+" | "+reciever.getUsername()+ " Report title: " + request.getTitle()
                + " Report description:" + request.getDescription());
        return notification;
    }

    Notification getRequestReceivedManagerNotifiaction(Request request,User user) {
        Notification notification = new Notification();
        notification.setUrl(getRequestUrl(request));
        notification.setUser(user);
        notification.setReaded(false);
        notification.setTitle("Request received");
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setDescription("You get reguest from:" +request.getUser().getUserdata().getName()+" "+request.getUser().getUserdata().getSurname()+" | "+ request.getUser().getUsername()
                + " Report title: " + request.getTitle()
                + " Report description:" + request.getDescription());
        return notification;
    }
}

