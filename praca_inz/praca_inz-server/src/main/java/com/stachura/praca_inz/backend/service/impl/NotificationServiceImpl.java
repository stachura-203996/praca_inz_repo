package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.repository.interfaces.NotificationRepository;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.NotificationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ')")
    public Notification getNotificationById(Long id) {
        return notificationRepository.find(id);
    }

    /*@Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ') and hasAuthority('DEPARTMENT_READ')")
    public Notification getNotificationByName(String name) {
        return notificationRepository.find(name);
    }*/


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_USER_LIST_READ')")
    public List<NotificationListElementDto> getAllNotificationsForUser() {
        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationListElementDto> notificationListElementDtos = new ArrayList<>();
        for (Notification a : notifications) {
            notificationListElementDtos.add(NotificationConverter.toNotificationListElementDto(a));
        }
        return notificationListElementDtos;
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_CREATE')")
    public void createNewNotification(Notification notification) {
        try {
            notificationRepository.create(notification);
        } catch (EntityException e) {

        }
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_UPDATE')")
    public Notification updateNotification(Notification notification) {
        Notification tmp = new Notification();
        try {
            tmp = notificationRepository.update(notification);
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_DELETE')")
    public void deleteNotificationById(Long id) {
        notificationRepository.remove(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_DELETE')")
    public void deleteNotification(Notification notification) {
        notificationRepository.remove(notification);
    }
}
