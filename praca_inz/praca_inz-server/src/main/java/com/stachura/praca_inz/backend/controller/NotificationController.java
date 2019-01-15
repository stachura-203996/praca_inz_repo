package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.NotificationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.util.List;

@RestController
@RequestMapping("/secured/notification")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AppBaseException.class)
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<NotificationListElementDto> getUnreadedAllDevicesForLoggedUser() {
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
    public ResponseEntity<?> create(@RequestBody NotificationListElementDto notificationListElementDto) throws AppBaseException {
        try {
            Notification notification = notificationService.getNotificationById(notificationListElementDto.getId());
            notificationService.createNewNotification(NotificationConverter.toNotification(notificationListElementDto, notification));
        } catch (OptimisticLockException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody NotificationListElementDto notificationListElementDto) throws AppBaseException {
        Notification notification = notificationService.getNotificationById(notificationListElementDto.getId());
        try {
            notificationService.updateNotification(NotificationConverter.toNotification(notificationListElementDto, notification));
        } catch (OptimisticLockException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        notificationService.deleteNotificationById(id);
    }
}
