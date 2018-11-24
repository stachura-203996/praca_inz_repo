package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Obiekt przesyłany do widoku z informacjami potrzebnymi do wyświetlenia listy użytkowników
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserListElementDto implements Serializable {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private boolean locked;
    private boolean verified;
}
