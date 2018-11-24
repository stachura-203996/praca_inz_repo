package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Obiekt przesyłany z/do widoku z informacjami potrzebnymi do edycji użytkownika
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEditDto implements Serializable {

    private String name;
    private String surname;
    private String city;
    private String street;
    private int houseNo;
    private Integer flatNo;
    private long userdataVersion;
}
