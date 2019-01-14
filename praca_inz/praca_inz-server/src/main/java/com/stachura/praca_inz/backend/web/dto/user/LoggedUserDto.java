package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoggedUserDto {

    private String username;
    private String name;
    private String surname;
    private Long companyId;
}
