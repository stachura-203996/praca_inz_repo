package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TransferAddDto {
    Long id;
    String title;
    Long recieverWarehouseId;
    Long deviceId;
}
