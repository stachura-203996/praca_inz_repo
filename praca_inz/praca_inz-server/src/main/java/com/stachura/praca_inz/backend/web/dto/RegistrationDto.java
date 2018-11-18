package com.stachura.praca_inz.backend.web.dto;

import com.stachura.praca_inz.backend.validation.PasswordMatches;
import com.stachura.praca_inz.backend.validation.ValidEmail;
import com.stachura.praca_inz.backend.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Obiekt przesyłany z widoku z informacjami potrzebnymi do rejestracju nowego konta
 */

@Getter
@Setter
@PasswordMatches
public class RegistrationDto {

    @NotNull
    @Size(min = 1, message = "{Size.userDto.username}")
    private String username;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;
    @ValidPassword
    private String password;
    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private String city;
    private String street;
    private String buildingNo;
    private String flatNo;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RegistrationDto [firstName=").append(", password=").append(password).append(", matchingPassword=").append(matchingPassword).append(", email=").append(email).append(", isUsing2FA=")
                .append("]");
        return builder.toString();
    }
}
