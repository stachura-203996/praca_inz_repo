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
    String createDate;
    String sender;
    String receiver;
    String serialNumber;
    boolean confirmed;
    private Long companyId;
    private Long warehouseId;
    private Long deviceModelId;
    private String deviceModelName;
}
