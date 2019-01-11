package com.stachura.praca_inz.backend.web.dto.warehouse;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class WarehouseAddDto {
    private String name;
    private Long userId;
    private Long officeId;
}
