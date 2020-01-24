package com.stachura.praca_inz.backend.web.dto.device;


import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceListElementDto {

    Long id;
    String serialNumber;
    String deviceModel;
    String deviceTypeName;
    String username;
    String manufacture;
    String lastUpdate;
    String location;
    String status;
    String name;
    String userSurname;
    String responsibleFor;
}
