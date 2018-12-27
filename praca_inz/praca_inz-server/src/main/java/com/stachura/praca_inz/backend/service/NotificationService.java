package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;

import java.util.List;

public interface NotificationService {
    
    Notification getNotificationById(Long id);

    List<NotificationListElementDto> getUnreadedAllNotificationsForLoggedUser(String username);

    List<NotificationListElementDto> getReadedAllNotificationsForLoggedUser(String username);

    void createNewNotification(Notification device);

    Notification updateNotification(Notification device);

    void deleteNotificationById(Long id);

    void deleteNotification(Notification device);
}
