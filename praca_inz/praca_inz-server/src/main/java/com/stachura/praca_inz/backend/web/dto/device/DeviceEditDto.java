package com.stachura.praca_inz.backend.web.dto.device;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceEditDto {
    private Long id ;
    private long version;
    private Long companyId;
    private Long warehouseId;
    private String serialNumber;
    private Long deviceModelId;
}
