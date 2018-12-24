package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.LoggedUserDto;
import com.stachura.praca_inz.backend.web.dto.ProfileInfoDto;
import com.stachura.praca_inz.backend.web.dto.UserInfoDto;
import com.stachura.praca_inz.backend.web.dto.UserListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.UserConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return UserConverter.toLoggedUser(user);
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
}
