package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;

import java.text.SimpleDateFormat;

public class NotificationConverter {

    public static NotificationListElementDto toNotificationListElementDto(Notification notification) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return NotificationListElementDto.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .description(notification.getDescription())
                .readed(notification.isReaded())
                .url(notification.getUrl())
                .calendarTimestamp(formatter.format(notification.getCalendarTimestamp().getTime()))
                .build();
    }

    /**
     * Metoda konwertująca  obiekt przesłany z widoku {@link NotificationListElementDto} na encję {@link Notification}
     *
     * @param notificationListElementDto dto powiadomienia z widoku
     * @param beforeNotification aktualna encja notyfikacji
     * @return obiekt z informacjami o użytkowniku
     */
    public static Notification toNotification(NotificationListElementDto notificationListElementDto, Notification beforeNotification) {
        beforeNotification.setTitle(notificationListElementDto.getTitle());
        beforeNotification.setDescription(notificationListElementDto.getDescription());
        beforeNotification.setReaded(notificationListElementDto.isReaded());
        beforeNotification.setUrl(notificationListElementDto.getUrl());
        return beforeNotification;
    }

}
