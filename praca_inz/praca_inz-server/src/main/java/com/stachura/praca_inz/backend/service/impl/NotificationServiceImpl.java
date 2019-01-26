package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.repository.NotificationRepository;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.NotificationConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    @Value("${server.port}")
    private String port;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('NOTIFICATION_READ')")
    public Notification getNotificationById(Long id) throws AppBaseException {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (notification.isDeleted()) {
            return null;
        }
        return notification;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('NOTIFICATION_LIST_READ')")
    public List<NotificationListElementDto> getUnreadedAllNotificationsForLoggedUser(String username) {
        List<Notification> notifications = Lists.newArrayList(notificationRepository.findAll()).stream().filter(x -> x.getUser().getUsername().equals(username) && !x.isReaded()).sorted(Comparator.comparing(Notification::getCreateDate).reversed()).collect(Collectors.toList());
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
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('NOTIFICATION_FOR_USER_READ')")
    public List<NotificationListElementDto> getReadedAllNotificationsForLoggedUser(String username) {
        List<Notification> notifications = Lists.newArrayList(notificationRepository.findAll()).stream().filter(x -> x.getUser().getUsername().equals(username)&&x.isReaded()).sorted(Comparator.comparing(Notification::getCreateDate).reversed()).collect(Collectors.toList());
        List<NotificationListElementDto> notificationListElementDtos = new ArrayList<>();
        for (Notification a : notifications) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getUser());
                notificationListElementDtos.add(NotificationConverter.toNotificationListElementDto(a));
            }
        }
        return notificationListElementDtos;
    }

    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('NOTIFICATION_USER_LIST_READ')")
    public List<NotificationListElementDto> getAllNotifications() {
        List<Notification> notifications =Lists.newArrayList(notificationRepository.findAll()).stream().sorted(Comparator.comparing(Notification::getCreateDate).reversed()).collect(Collectors.toList());
        List<NotificationListElementDto> notificationListElementDtos = new ArrayList<>();
        for (Notification a : notifications) {
            if (!a.isDeleted()) {
                notificationListElementDtos.add(NotificationConverter.toNotificationListElementDto(a));
            }
        }
        return notificationListElementDtos;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('NOTIFICATION_CREATE')")
    public void createNewNotification(Notification notification)throws AppBaseException {
            notificationRepository.saveAndFlush(notification);
            String link = "<a href=\"http://localhost:"+ port+"/ui/page" +notification.getUrl()+"\">Click</a>";
            String description="<p>"+notification.getDescription()
                    .replace("Opis","<br> Opis")
                    .replace("Description","<br> Description")
                    .replace("Title","<br> Title")
                    .replace("Tytuł","<br> Tytuł")
                    +"<p><br>"+link;
            emailService.sendMessageWithLink(notification.getUser().getUserdata().getEmail(),notification.getTitle(),description);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('NOTIFICATION_UPDATE')")
    public void updateNotification(Notification notification) throws AppBaseException {
        notificationRepository.saveAndFlush(notification);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('NOTIFICATION_DELETE')")
    public void deleteNotificationById(Long id) throws AppBaseException {
        notificationRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

}
