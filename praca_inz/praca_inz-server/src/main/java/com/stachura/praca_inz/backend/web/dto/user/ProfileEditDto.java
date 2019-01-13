package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProfileEditDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String position;
    private String workplace;
    private Long officeId;
    private long versionUser;
    private long versionUserdata;
    private long versionWarehouse;
    private String officeName;
}
