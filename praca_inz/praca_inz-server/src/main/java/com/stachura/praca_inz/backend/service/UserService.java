package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.web.dto.user.*;

import java.util.List;

public interface UserService {

    List<UserListElementDto> getAllUsers();

    ProfileInfoDto getProfile(String name) throws AppBaseException;

    ProfileEditDto getProfileEdit(String id) throws AppBaseException;

    LoggedUserDto getLoggedUser(String name) throws AppBaseException;

    UserInfoDto getUserInfo(String name) throws AppBaseException;

    UserEditDto getUserEdit(Long id) throws AppBaseException;

    List<UserListElementDto> getAllUsersForManager(String username) throws AppBaseException;

    List<UserListElementDto> getAllUsersForCompanyAdmin(String username) throws AppBaseException;

    List<UserListElementDto> getAllWarehousemen(Long id);

    UserRolesDto getLoggedUserRoles(String name) throws AppBaseException;

    UserRolesDto getUserRoles(Long id) throws AppBaseException;

    void deleteUser(Long id) throws AppBaseException;

    PasswordInfoForAdmin getPasswordForAdmin(Long id) throws AppBaseException;

    void updatePasswordForAdmin(PasswordInfoForAdmin passwordInfoForAdmin) throws AppBaseException;

    PasswordInfoDto getPassword(String username) throws AppBaseException;

    void updatePassword(PasswordInfoDto passwordInfoDto, String username) throws AppBaseException;

    void resetPassword(String email) throws AppBaseException;
}
