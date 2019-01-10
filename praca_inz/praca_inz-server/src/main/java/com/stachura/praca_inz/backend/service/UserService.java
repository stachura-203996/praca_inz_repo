package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.web.dto.user.*;

import java.util.List;

public interface UserService {

    List<UserListElementDto> getAllUsers();

    ProfileInfoDto getProfile(String name);

    LoggedUserDto getLoggedUser(String name);

    UserInfoDto getUserInfo(String name);

    List<UserListElementDto> getAllUsersForManager(String username);

    List<UserListElementDto> getAllUsersForCompanyAdmin(String username);

    List<UserListElementDto> getAllWarehousemen(Long id);

    void updateUser(User user) throws ServiceException;

    UserRolesDto getLoggedUserRoles(String name);


}
