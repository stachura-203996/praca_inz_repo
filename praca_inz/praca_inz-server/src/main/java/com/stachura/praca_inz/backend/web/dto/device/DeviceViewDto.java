package com.stachura.praca_inz.backend.web.dto.device;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceViewDto {
    Long id;
    String serialNumber;
    String deviceModel;
    Long deviceModelId;
    String deviceTypeName;
    String username;
    String manufacture;
    String lastUpdate;
    String location;
    String status;
    String userName;
    String userSurname;
    String responsibleFor;
}
