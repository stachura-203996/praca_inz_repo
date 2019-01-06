package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Report;
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

import java.util.Calendar;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    RequestViewDto getRequestById(@PathVariable Long id) {
        return RequestConverter.toRequestView(requestService.getRequestById(id));
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
            requestService.createNewRequest(RequestConverter.toRequest(transferRequestAddDto, deviceRepository, warehouseRepository, userRepository.find(auth.getName())));
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

    @RequestMapping(value = "/shipment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createShipmentRequest(@RequestBody ShipmentRequestAddDto shipmentRequestAddDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            requestService.createNewRequest(RequestConverter.toRequest(shipmentRequestAddDto, deviceRepository, warehouseRepository, userRepository.find(auth.getName())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
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


    String getRequestUrl(Request request, String port, Long id) {
        String url=null;
        switch (request.getRequestType()) {
            case DEVICE_REQUEST:
                url="<a href=\"\"http://localhost:\"" + port + "\"/page/employees/reports/view" + id + "\">Click</a>";
                break;
            case TRANSFER_REQUEST:
                url="<a href=\"\"http://localhost:\"" + port + "\"/page/employees/reports/view" + id + "\">Click</a>";
                break;
            case DELIVERY_REQUEST:
                url="<a href=\"\"http://localhost:\"" + port + "\"/page/employees/reports/view" + id + "\">Click</a>";
                break;

            case SHIPMENT_REQUEST:
                url= "<a href=\"\"http://localhost:\"" + port + "\"/page/employees/reports/view" + id + "\">Click</a>";
                break;
        }
        return url;
    }

    Notification getRequestSentNotifiaction(Request request, String port, Long id) {
        String link = getRequestUrl(request, port, id);
        Notification notification = new Notification();
        notification.setUrl("localhost:" + port + "/page/employees/reports/view" + id);
        notification.setUser(request.getUser());
        notification.setReaded(false);
        notification.setTitle("Report received");
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setDescription("Your request was sent to:" + request.getRecieverWarehouse().getUser().getUsername() + "\n+ \n"
                + "Report title: " + request.getTitle() + "\n \n"
                + "Report description:" + request.getDescription() + "\n \n"
                + link);
        return notification;
    }

    Notification getRequestReceivedNotifiaction(Request request, String port, Long id) {
        String link = getRequestUrl(request, port, id);
        Notification notification = new Notification();
        notification.setUrl("localhost:" + port + "/page/employees/reports/view" + id);
        notification.setUser(request.getRecieverWarehouse().getUser());
        notification.setReaded(false);
        notification.setTitle("Report received");
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setDescription("You get report from:" + request.getSenderWarehouse().getUser().getUsername() + "\n+ \n"
                + "Report title: " + request.getTitle() + "\n \n"
                + "Report description:" + request.getDescription() + "\n \n"
                + "localhost:" + port + "/page/employees/reports/view" + id);
        return notification;
    }
}

