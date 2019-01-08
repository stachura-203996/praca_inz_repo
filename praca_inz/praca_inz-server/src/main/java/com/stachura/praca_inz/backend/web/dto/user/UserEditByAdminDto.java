package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

import java.io.Serializable;

/**
 * Obiekt przesyłany z/do widoku z informacjami potrzebnymi do edycji użytkownika przez administratora
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEditByAdminDto implements Serializable {

    private String supervisorUsername;
    private String name;
    private String surname;
    private String city;
    private String street;
    private int houseNo;
    private Integer flatNo;
    private String email;
    private long userdataVersion;
}
