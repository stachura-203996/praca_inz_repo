package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

/**
 * Obiekt przesyłany do widoku z informacjami potrzebnymi do wyświetlenia listy firm, departamentów, biur
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CompanyStructuresListElementDto {

    private Long id;
    private String name;
    private String description;
    private String city;
    private String street;
    private String buildingNumber;
    private String flatNumber;
    private String zipCode;
    private String parentName;
}
