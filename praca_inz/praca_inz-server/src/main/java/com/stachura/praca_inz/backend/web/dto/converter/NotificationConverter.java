package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;

public class NotificationConverter {

    public static NotificationListElementDto toNotificationListElementDto(Notification notification){
        return NotificationListElementDto.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .description(notification.getDescription())
                .build();
    }
}
