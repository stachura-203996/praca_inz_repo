package com.stachura.praca_inz.backend.web.dto.warehouse;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class WarehouseViewDto {
    private String name;
    private String userName;
    private String  userSurname;
    private String username;
    private String officeName;
    private String companyName;
    private String departmentName;
    private String devicesNumber;
}
