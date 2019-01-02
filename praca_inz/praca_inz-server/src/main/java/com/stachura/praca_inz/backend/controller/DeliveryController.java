package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.service.DeliveryService;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeliveryListElementDto> getAll() {
        return deliveryService.getAllDeliveries();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Delivery get(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Delivery delivery) {
        try {
            deliveryService.createNewDelivery(delivery);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(CompanyController.class).getOfficeById(delivery.getId()));
//        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Delivery delivery) {
        try {
            deliveryService.updateDelivery(delivery);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        deliveryService.deleteDeliveryById(id);
    }
}
