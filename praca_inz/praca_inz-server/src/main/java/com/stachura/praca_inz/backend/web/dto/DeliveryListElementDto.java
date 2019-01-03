package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeliveryListElementDto {
    Long id;
    String title;
    String deliveryNumber;
    String username;
    String status;
    String createDate;
    String lastUpdate;
    String sender;
    String receiver;
}
