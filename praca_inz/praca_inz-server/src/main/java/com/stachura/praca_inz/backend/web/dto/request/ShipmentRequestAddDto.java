package com.stachura.praca_inz.backend.web.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ShipmentRequestAddDto {
    private Long id;
    private String title;
    private String description;
    private Long  deviceId;
    private Long recieverWarehouseId;
}
