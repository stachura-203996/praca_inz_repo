package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRolesDto {
    boolean admin;
    boolean company_admin;
    boolean manager;
    boolean warehouseman;
    boolean user;

}
