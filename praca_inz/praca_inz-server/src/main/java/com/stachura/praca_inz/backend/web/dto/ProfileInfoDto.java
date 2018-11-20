package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

/**
 * Obiekt przesyłany do widoku z informacjami o zalogwanym użytkowniku
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProfileInfoDto {

    private Long id;
    private String name;
    private String surname;
    private String city;
    private String email;
    private String street;
    private int houseNo;
    private Integer flatNo;
    private int pointsSum;
}