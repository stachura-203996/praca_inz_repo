package com.stachura.praca_inz.backend.web.dto.company;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CompanyStructureAddDto {
    private String name;
    private String description;
    private String city;
    private String street;
    private String buildingNumber;
    private String flatNumber;
    private String zipCode;
    private String companyId;
    private String departmentId;
}
