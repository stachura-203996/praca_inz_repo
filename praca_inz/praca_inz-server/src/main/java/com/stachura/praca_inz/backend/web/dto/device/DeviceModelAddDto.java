package com.stachura.praca_inz.backend.web.dto.device;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceModelAddDto {
    private String name;
    private String manufacture;
    private Long typeId;
    private long cost;
    private Long companyId;
}
