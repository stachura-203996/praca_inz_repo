package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

import java.io.Serializable;

/**
 * Obiekt przesyłany z/do widoku z informacjami potrzebnymi do zmiany hasła użytkownika przez administratora
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PasswordInfoForAdmin implements Serializable {

    private Long id;
    private String newPassword;
    private long userdataVersion;
}
