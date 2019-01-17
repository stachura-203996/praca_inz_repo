package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginDto {

    private String login;
    private String password;
}
