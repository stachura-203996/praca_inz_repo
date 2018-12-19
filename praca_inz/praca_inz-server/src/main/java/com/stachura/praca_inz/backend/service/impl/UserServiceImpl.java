package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.ProfileInfoDto;
import com.stachura.praca_inz.backend.web.dto.UserListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.UserConverter;
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
    public ProfileInfoDto get(String name) {
        User user = userRepository.findByUsername(name);
        return UserConverter.toProfileInfo(user);
    }
}
