package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Obiekt przesyłany z/do widoku z informacjami potrzebnymi do zmiany hasła użytkownika
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PasswordInfoDto implements Serializable {

    private String newPassword;
    private String oldPassword;
    private long userdataVersion;
}
