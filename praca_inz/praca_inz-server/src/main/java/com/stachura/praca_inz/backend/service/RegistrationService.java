package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.web.dto.user.RegistrationDto;

public interface RegistrationService {
    void registerNewUserAccount(RegistrationDto data,boolean verified) throws AppBaseException;
}
