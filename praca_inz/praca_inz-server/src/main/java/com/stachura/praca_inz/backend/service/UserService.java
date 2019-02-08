package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.web.dto.PasswordResetDto;
import com.stachura.praca_inz.backend.web.dto.user.*;

import java.util.List;

public interface UserService {

    List<UserListElementDto> getAllUsers();

    ProfileInfoDto getProfile(String name) throws SystemBaseException;

    ProfileEditDto getProfileEdit(String id) throws SystemBaseException;

    LoggedUserDto getLoggedUser(String name) throws SystemBaseException;

    UserInfoDto getUserInfo(String name) throws SystemBaseException;

    UserEditDto getUserEdit(Long id) throws SystemBaseException;

    List<UserListElementDto> getAllUsersForManager(String username) throws SystemBaseException;

    List<UserListElementDto> getAllUsersForCompanyAdmin(String username) throws SystemBaseException;

    List<UserListElementDto> getAllWarehousemen(Long id);

    UserRolesDto getLoggedUserRoles(String name) throws SystemBaseException;

    UserRolesDto getUserRoles(Long id) throws SystemBaseException;

    void deleteUser(Long id) throws SystemBaseException;

    PasswordInfoForAdmin getPasswordForAdmin(Long id) throws SystemBaseException;

    void updatePasswordForAdmin(PasswordInfoForAdmin passwordInfoForAdmin) throws SystemBaseException;

    PasswordInfoDto getPassword(String username) throws SystemBaseException;

    void updatePassword(PasswordInfoDto passwordInfoDto, String username) throws SystemBaseException;

    void resetPassword(PasswordResetDto email) throws SystemBaseException;

    List<UserListElementDto> getAllUsersForReport(String name);
}
