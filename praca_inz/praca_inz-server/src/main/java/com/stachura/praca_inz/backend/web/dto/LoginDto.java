package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

/**
 * Obiekt przesyłany z widoku z informacjami potrzebnymi do zalogowania się użytkownika
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginDto {

    private String login;
    private String password;
}
