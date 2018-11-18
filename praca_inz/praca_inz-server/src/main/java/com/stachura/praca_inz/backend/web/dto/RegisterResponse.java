package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

/**
 * Obiekt przesyłany do widoku z odpowiedzią po przeprowadzonej rejestracji użytkownika
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RegisterResponse {

    private String message;
}
