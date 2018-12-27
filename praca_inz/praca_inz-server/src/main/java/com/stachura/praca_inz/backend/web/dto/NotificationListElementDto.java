package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NotificationListElementDto {

    private Long id;
    private String title;
    private String description;
    private String url;
    private boolean readed;
    private String calendarTimestamp;

}
