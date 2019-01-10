package com.stachura.praca_inz.backend.web.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RequestListElementDto {
    private Long id;
    private String title;
    private String username;
    private String status;
    private String type;
    private boolean acceptedToSend;
    private boolean acceptedToRecive;
    private String recieverWarehouseName;
    private String senderWarehouseName;
    private String createDate;
    private long version;
}
