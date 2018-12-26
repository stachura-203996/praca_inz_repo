package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.web.dto.RegistrationDto;

public interface RegistrationService {
    void registerNewUserAccount(RegistrationDto data,boolean verified);
}
