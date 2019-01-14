package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;

import java.util.List;

public interface NotificationService {
    
    Notification getNotificationById(Long id) throws AppBaseException;

    List<NotificationListElementDto> getUnreadedAllNotificationsForLoggedUser(String username);

    List<NotificationListElementDto> getReadedAllNotificationsForLoggedUser(String username);

    void createNewNotification(Notification notification) throws AppBaseException;

    void updateNotification(Notification notification) throws AppBaseException;

    void deleteNotificationById(Long id) throws AppBaseException;
}
