package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.web.dto.user.*;

import java.util.List;

public interface UserService {

    List<UserListElementDto> getAllUsers();

    ProfileInfoDto getProfile(String name) throws ServiceException;

    ProfileEditDto getProfileEdit(String id) throws ServiceException;

    LoggedUserDto getLoggedUser(String name) throws ServiceException;

    UserInfoDto getUserInfo(String name) throws ServiceException;

    UserEditDto getUserEdit(Long id) throws ServiceException;

    List<UserListElementDto> getAllUsersForManager(String username) throws ServiceException;

    List<UserListElementDto> getAllUsersForCompanyAdmin(String username) throws ServiceException;

    List<UserListElementDto> getAllWarehousemen(Long id);

    UserRolesDto getLoggedUserRoles(String name) throws ServiceException;

    UserRolesDto getUserRoles(Long id) throws ServiceException;

    void deleteUser(Long id) throws ServiceException;

    PasswordInfoForAdmin getPasswordForAdmin(Long id) throws ServiceException;

    void updatePasswordForAdmin(PasswordInfoForAdmin passwordInfoForAdmin) throws ServiceException;

    PasswordInfoDto getPassword(String username) throws ServiceException;

    void updatePassword(PasswordInfoDto passwordInfoDto, String username) throws ServiceException;

    void resetPassword(String email) throws ServiceException;
}
