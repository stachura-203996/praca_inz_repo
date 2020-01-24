package com.stachura.praca_inz.backend.validation;

import com.stachura.praca_inz.backend.web.dto.user.RegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final RegistrationDto user = (RegistrationDto) obj;
        return user.getPassword().equals(user.getPassword());
    }

}
