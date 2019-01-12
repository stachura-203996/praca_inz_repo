package com.stachura.praca_inz.backend.web.dto.device;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceModelViewDto {

    Long id;
    String name;
    String manufacture;
    String numberOfDevices;
    String type;
    long cost;
    String companyName;
}
