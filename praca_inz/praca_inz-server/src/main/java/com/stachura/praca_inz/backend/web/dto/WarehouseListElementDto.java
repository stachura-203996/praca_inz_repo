package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class WarehouseListElementDto {

    private Long id;
    private String name;
    private String username;
    private String officeId;
    private String officeName;
    private String devicesNumber;
}
