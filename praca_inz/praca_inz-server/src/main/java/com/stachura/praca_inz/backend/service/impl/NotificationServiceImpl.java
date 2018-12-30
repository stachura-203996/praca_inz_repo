package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.repository.interfaces.NotificationRepository;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.NotificationConverter;
import com.stachura.praca_inz.backend.web.dto.converter.TransferConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ')")
    public Notification getNotificationById(Long id) {
        Notification notification = notificationRepository.find(id);
        if (notification.isDeleted()) {
            return null;
        }
        return notification;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ')")
    public List<NotificationListElementDto> getUnreadedAllNotificationsForLoggedUser(String username) {
        List<Notification> notifications = notificationRepository.findAll().stream().filter(x -> x.getUser().getUsername().equals(username) && !x.isReaded()).sorted(Comparator.comparing(Notification::getCalendarTimestamp).reversed()).collect(Collectors.toList());
        List<NotificationListElementDto> notificationListElementDtos = new ArrayList<>();
        for (Notification a : notifications) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getUser());
                notificationListElementDtos.add(NotificationConverter.toNotificationListElementDto(a));
            }
        }
        return notificationListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ')")
    public List<NotificationListElementDto> getLast3UnreadedAllNotificationsForLoggedUser(String username) {
        List<Notification> notifications = notificationRepository.findAll().stream().filter(x -> x.getUser().getUsername().equals(username) && !x.isReaded()).sorted(Comparator.comparing(Notification::getCalendarTimestamp).reversed()).collect(Collectors.toList());
        List<NotificationListElementDto> notificationListElementDtos = new ArrayList<>();
        int i=0;
        for (Notification a : notifications) {
            if (!a.isDeleted()&& i<3) {
                Hibernate.initialize(a.getUser());
                notificationListElementDtos.add(NotificationConverter.toNotificationListElementDto(a));
                i++;
            }
        }
        return notificationListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ')")
    public List<NotificationListElementDto> getReadedAllNotificationsForLoggedUser(String username) {
        List<Notification> notifications = notificationRepository.findAll().stream().filter(x -> x.getUser().getUsername().equals(username)&&x.isReaded()).sorted(Comparator.comparing(Notification::getCalendarTimestamp).reversed()).collect(Collectors.toList());
        List<NotificationListElementDto> notificationListElementDtos = new ArrayList<>();
        for (Notification a : notifications) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getUser());
                notificationListElementDtos.add(NotificationConverter.toNotificationListElementDto(a));
            }
        }
        return notificationListElementDtos;
    }

    /*@Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ') and hasAuthority('DEPARTMENT_READ')")
    public Notification getNotificationByName(String name) {
        return notificationRepository.find(name);
    }*/


    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('NOTIFICATION_USER_LIST_READ')")
    public List<NotificationListElementDto> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll().stream().sorted(Comparator.comparing(Notification::getCalendarTimestamp).reversed()).collect(Collectors.toList());
        List<NotificationListElementDto> notificationListElementDtos = new ArrayList<>();
        for (Notification a : notifications) {
            if (!a.isDeleted()) {
                notificationListElementDtos.add(NotificationConverter.toNotificationListElementDto(a));
            }
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
            if (!notificationRepository.find(notification.getId()).isDeleted()) {
                tmp = notificationRepository.update(notification);
            }
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_DELETE')")
    public void deleteNotificationById(Long id) {
        notificationRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_DELETE')")
    public void deleteNotification(Notification notification) {
        notificationRepository.find(notification.getId()).setDeleted(true);
    }
}
