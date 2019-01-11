package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import com.stachura.praca_inz.backend.web.dto.converter.NotificationConverter;
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
@RequestMapping("/secured/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;

    /**
     * Metoda zwracająca obiekt typu {@link List<NotificationListElementDto>} składający się z aktualnie dostępnych benefitów na wymiane
     *
     * @return lista dostepnych benefitów
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<NotificationListElementDto> getUnreadedAllDevicesForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notificationService.getUnreadedAllNotificationsForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/user/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<NotificationListElementDto> getLast3UnreadedAllDevicesForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notificationService.getUnreadedAllNotificationsForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/user/readed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<NotificationListElementDto> getReadedAllDevicesForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notificationService.getReadedAllNotificationsForLoggedUser(auth.getName());
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody NotificationListElementDto notificationListElementDto) {
        try {
            Notification notification=notificationService.getNotificationById(notificationListElementDto.getId());
            notificationService.createNewNotification(NotificationConverter.toNotification(notificationListElementDto,notification));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(OfficeController.class).getOfficeById(office.getId()));
//        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody NotificationListElementDto notificationListElementDto) {
        Notification notification= notificationService.getNotificationById(notificationListElementDto.getId());
        try {
            notificationService.updateNotification(NotificationConverter.toNotification(notificationListElementDto,notification));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        notificationService.deleteNotificationById(id);
    }
}
