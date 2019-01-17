package com.stachura.praca_inz.backend.web.dto.user;

import com.stachura.praca_inz.backend.validation.PasswordMatches;
import com.stachura.praca_inz.backend.validation.ValidEmail;
import com.stachura.praca_inz.backend.validation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Obiekt przesyłany z widoku z informacjami potrzebnymi do rejestracju nowego konta
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RegistrationDto {

    private String username;
    private String password;
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
    private List<String> roles;
    private String language;
}
