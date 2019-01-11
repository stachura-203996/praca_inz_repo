package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;

import java.util.List;

public interface NotificationService {
    
    Notification getNotificationById(Long id) throws ServiceException;

    List<NotificationListElementDto> getUnreadedAllNotificationsForLoggedUser(String username);

    List<NotificationListElementDto> getReadedAllNotificationsForLoggedUser(String username);

    void createNewNotification(Notification notification) throws ServiceException;

    void updateNotification(Notification notification) throws ServiceException;

    void deleteNotificationById(Long id) throws ServiceException;
}
