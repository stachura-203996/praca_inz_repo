package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PasswordInfoDto implements Serializable {

    private String newPassword;
    private String oldPassword;
    private long userVersion;
}
