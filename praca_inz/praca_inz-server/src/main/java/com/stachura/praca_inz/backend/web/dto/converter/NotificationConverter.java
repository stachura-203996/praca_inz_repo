package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.web.dto.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;

public class NotificationConverter {

    public static NotificationListElementDto toNotificationListElementDto(Notification notification){
        return NotificationListElementDto.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .description(notification.getDescription())
                .readed(notification.isReaded())
                .url(notification.getUrl())
                .calendarTimestamp(notification.getCalendarTimestamp().getTime().toString())
                .build();
    }

    public static Notification toNotification(NotificationListElementDto notificationListElementDto, Notification beforeNotification) {
        beforeNotification.setTitle(notificationListElementDto.getTitle());
        beforeNotification.setDescription(notificationListElementDto.getDescription());
        beforeNotification.setReaded(notificationListElementDto.isReaded());
        beforeNotification.setUrl(notificationListElementDto.getUrl());
        return beforeNotification;
    }
}
