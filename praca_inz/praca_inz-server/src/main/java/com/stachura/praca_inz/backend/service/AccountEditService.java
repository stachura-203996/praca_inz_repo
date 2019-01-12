package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.web.dto.user.ProfileEditDto;
import com.stachura.praca_inz.backend.web.dto.user.UserEditDto;


public interface AccountEditService {

    void updateAccountbyAdmin(UserEditDto userEditDto) throws ServiceException;

    void updateProfileByUser(ProfileEditDto profileEditDto) throws ServiceException;
}
