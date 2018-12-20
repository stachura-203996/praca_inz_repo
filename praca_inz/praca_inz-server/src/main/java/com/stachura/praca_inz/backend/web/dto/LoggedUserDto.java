package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

import java.util.List;

/**
 * Obiekt przesyłany do widoku z informacjami o zalogowanym użytkowniku
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoggedUserDto {

    private String username;
    private String name;
    private String surname;
    private List<String> roles;
}
