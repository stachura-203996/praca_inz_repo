package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceModelViewDto {

    Long id;
    String name;
    String manufacture;
    String owner;
    String numberOfDevices;
    String type;
}
