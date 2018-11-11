package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.web.dto.RegistrationDto;

public interface IRegistrationService {
    User registerNewUserAccount(RegistrationDto accountDto);
}
