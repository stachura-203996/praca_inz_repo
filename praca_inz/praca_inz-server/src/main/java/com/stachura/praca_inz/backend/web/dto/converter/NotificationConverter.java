package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;

import java.text.SimpleDateFormat;

public class NotificationConverter {

    //LIST
    public static NotificationListElementDto toNotificationListElementDto(Notification notification) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return NotificationListElementDto.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .description(notification.getDescription())
                .readed(notification.isReaded())
                .url(notification.getUrl())
                .calendarTimestamp(formatter.format(notification.getCreateDate().getTime()))
                .build();
    }

    //CHANGE NOTIFICATION STATUS
    public static Notification toNotification(NotificationListElementDto notificationListElementDto, Notification beforeNotification) {
        beforeNotification.setTitle(notificationListElementDto.getTitle());
        beforeNotification.setDescription(notificationListElementDto.getDescription());
        beforeNotification.setReaded(notificationListElementDto.isReaded());
        beforeNotification.setUrl(notificationListElementDto.getUrl());
        return beforeNotification;
    }

}
