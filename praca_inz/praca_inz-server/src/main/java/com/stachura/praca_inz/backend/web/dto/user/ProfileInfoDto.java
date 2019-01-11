package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProfileInfoDto {

    private Long id;
    private String username;
    private String position;
    private String company;
    private Long companyId;
    private String department;
    private Long departmentId;
    private String office;
    private Long officeId;
    private String name;
    private String surname;
    private String city;
    private String email;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String workplace;
    private List<String> roles;
    private String dateOfJoining;
}
