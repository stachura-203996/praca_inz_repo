package com.stachura.praca_inz.backend.web.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeliveryRequestAddDto {
    private Long id;
    private String title;
    private int amount;
    private String description;
    private Long  deviceModelId;
    private String recieverWarehouseId;
}
