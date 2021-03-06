package com.stachura.praca_inz.backend.web.dto.device;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DeviceModelEditDto {
    private Long id;
    private String name;
    private String manufacture;
    private String type;
    private Long typeId;
    private long cost;
    private Long companyId;
    private String companyname;
    private long version;
}
