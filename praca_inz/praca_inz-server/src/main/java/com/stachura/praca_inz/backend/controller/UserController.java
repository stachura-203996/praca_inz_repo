package com.stachura.praca_inz.backend.controller;


import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/users")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ProfileInfoDto getProfileInfo() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getProfile(auth.getName());
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    PasswordInfoDto getPassword() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getPassword(auth.getName());
    }

    @RequestMapping(value = "/password/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    PasswordInfoForAdmin getPasswordForAdmin(@PathVariable Long id) throws SystemBaseException {
        return userService.getPasswordForAdmin(id);
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePassword(@RequestBody PasswordInfoDto passwordInfoDto) throws SystemBaseException {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userService.updatePassword(passwordInfoDto, auth.getName());
    }

    @RequestMapping(value = "/password/admin", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePasswordAdmin(@RequestBody PasswordInfoForAdmin passwordInfoForAdmin) throws SystemBaseException {
            userService.updatePasswordForAdmin(passwordInfoForAdmin);
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ProfileEditDto getProfileEdit() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getProfileEdit(auth.getName());
    }

    @RequestMapping(value = "/view/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserInfoDto getUserInfo(@PathVariable String username) throws SystemBaseException {
        return userService.getUserInfo(username);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserEditDto getUserEdit(@PathVariable Long id) throws SystemBaseException {
        return userService.getUserEdit(id);
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    LoggedUserDto getLoggedUserInfo() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/logged/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserRolesDto getLoggedUserRoles() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getLoggedUserRoles(auth.getName());
    }

    @RequestMapping(value = "/user/roles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserRolesDto getUserRoles(@PathVariable Long id) throws SystemBaseException {
        return userService.getUserRoles(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getAll() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/company/admin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getAllForCompanyAdmin() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getAllUsersForCompanyAdmin(auth.getName());
    }

    @RequestMapping(value = "/subordinates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getAllForManager() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getAllUsersForManager(auth.getName());
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getAllUsersForReport() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getAllUsersForReport(auth.getName());
    }

    @RequestMapping(value = "/warehouse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getAllUsersForWarehouseEdit(@PathVariable Long id) throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getAllUsersForWarehouseEdit(id);
    }

    @RequestMapping(value = "/warehousemen/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getAllWarehousemen(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getAllWarehousemen(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        userService.deleteUser(id);
    }
}
