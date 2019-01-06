package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.repository.interfaces.NotificationRepository;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.NotificationConverter;
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

    @Autowired
    private EmailService emailService;

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

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_CREATE')")
    public void createNewNotification(Notification notification)throws ServiceException {
        try {
            notificationRepository.create(notification);
            emailService.sendMessageWithLink(notification.getUser().getUserdata().getEmail(),notification.getTitle(),notification.getDescription());
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('NOTIFICATION_UPDATE')")
    public void updateNotification(Notification notification) throws ServiceException {
        notificationRepository.update(notification);
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
