package com.stachura.praca_inz.backend.web.dto;

import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TransferListElementDto {

    private Long id;
    private String title;
    private String date;
    private String senderWarehouseName;
    private String recieverWarehouseName;
    private String deviceName;
    private String deviceSerialNumber;
}
