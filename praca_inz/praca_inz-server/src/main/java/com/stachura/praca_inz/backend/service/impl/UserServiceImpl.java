package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.user.*;
import com.stachura.praca_inz.backend.web.dto.converter.UserConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserListElementDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            usersDto.add(UserConverter.toUserListElement(a));
        }
        return usersDto;
    }

    @Override
    public ProfileInfoDto getProfile(String name) {
        User user = userRepository.find(name);
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        return UserConverter.toProfileInfo(user);
    }

    @Override
    public LoggedUserDto getLoggedUser(String name) {
        User user = userRepository.find(name);
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        LoggedUserDto loggedUserDto=UserConverter.toLoggedUser(user);
        return loggedUserDto;
    }

    @Override
    public UserInfoDto getUserInfo(String name) {
        User user = userRepository.find(name);
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        return UserConverter.toUserInfo(user);
    }

    @Override
    public List<UserListElementDto> getAllUsersForManager(String username) {
        Long officeId=userRepository.find(username).getOffice().getId();
        List<User> users=userRepository.findAll().stream().filter(x->x.getOffice().getDepartment().getCompany().getId().equals(officeId)).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            usersDto.add(UserConverter.toUserListElement(a));
        }
        return usersDto;
    }

    @Override
    public List<UserListElementDto> getAllUsersForCompanyAdmin(String username) {
        Long companyId=userRepository.find(username).getOffice().getDepartment().getCompany().getId();
        List<User> users=userRepository.findAll().stream().filter(x->x.getOffice().getDepartment().getCompany().getId().equals(companyId)).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            usersDto.add(UserConverter.toUserListElement(a));
        }
        return usersDto;
    }

    @Override
    public List<UserListElementDto> getAllWarehousemen(Long id) {
        List<User> users=userRepository.findAll().stream().filter(x->x.getOffice().getId().equals(id)&&x.getUserRoles().contains("WAREHOUSEMAN")).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            usersDto.add(UserConverter.toUserListElement(a));
        }
        return usersDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public void updateUser(User user) throws ServiceException {
        userRepository.update(user);
    }

    @Override
    public UserRolesDto getLoggedUserRoles(String name) {
        User user = userRepository.find(name);
        return UserConverter.toUserRoles(user);
    }


}
