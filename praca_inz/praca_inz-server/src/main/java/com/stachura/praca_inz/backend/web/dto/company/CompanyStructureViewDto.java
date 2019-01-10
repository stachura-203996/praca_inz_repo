package com.stachura.praca_inz.backend.web.dto.company;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CompanyStructureViewDto {
    Long id;
    String name;
    String description;
    String city;
    String street;
    String buildingNumber;
    String flatNumber;
    String companyName;
    String departmentName;
}
