package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.web.dto.user.*;

import java.util.List;

public interface UserService {

    List<UserListElementDto> getAllUsers();

    ProfileInfoDto getProfile(String name) throws ServiceException;

    LoggedUserDto getLoggedUser(String name) throws ServiceException;

    UserInfoDto getUserInfo(String name) throws ServiceException;

    List<UserListElementDto> getAllUsersForManager(String username) throws ServiceException;

    List<UserListElementDto> getAllUsersForCompanyAdmin(String username) throws ServiceException;

    List<UserListElementDto> getAllWarehousemen(Long id);

    void updateUser(User user) throws ServiceException;

    UserRolesDto getLoggedUserRoles(String name) throws ServiceException;


}
