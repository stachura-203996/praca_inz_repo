package com.stachura.praca_inz.backend.web.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceListElementDto {

    Long id;
    String name;
    String serialNumber;
    String deviceTypeName;
    String username;

}
