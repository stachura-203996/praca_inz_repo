package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.ServiceException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.user.*;
import com.stachura.praca_inz.backend.web.dto.converter.UserConverter;
import com.stachura.praca_inz.backend.web.utils.PasswordGenerator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Override
    public List<UserListElementDto> getAllUsers() {
        List<User> users = Lists.newArrayList(userRepository.findAll());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }

    @Override
    public ProfileInfoDto getProfile(String name) throws AppBaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        if (user.isEnabled()) {
            return UserConverter.toProfileInfo(user);
        }
        return null;
    }

    @Override
    public ProfileEditDto getProfileEdit(String username) throws AppBaseException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());

        if (user.isEnabled()) {
            return UserConverter.toProfileEditDto(user, user.getWarehouses().stream().filter(x -> x.getWarehouseType().equals(WarehouseType.USER)).findFirst().orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        }
        return null;
    }

    @Override
    public LoggedUserDto getLoggedUser(String name) throws AppBaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        LoggedUserDto loggedUserDto = UserConverter.toLoggedUser(user);
        return loggedUserDto;
    }

    @Override
    public UserInfoDto getUserInfo(String name) throws AppBaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        if (user.isEnabled()) {
            return UserConverter.toUserInfo(user);
        }
        return null;
    }

    @Override
    public UserEditDto getUserEdit(Long id) throws AppBaseException {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        if (user.isEnabled()) {
            return UserConverter.toUserEditDto(user, user.getWarehouses().stream().filter(x -> x.getWarehouseType().equals(WarehouseType.USER)).findFirst().orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        }
        return null;
    }

    @Override
    public List<UserListElementDto> getAllUsersForManager(String username) throws AppBaseException {
        Long officeId = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).getOffice().getId();
        List<User> users = Lists.newArrayList(userRepository.findAll()).stream().filter(x -> x.getOffice().getDepartment().getCompany().getId().equals(officeId)).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }

    @Override
    public List<UserListElementDto> getAllUsersForCompanyAdmin(String username) throws AppBaseException {
        Long companyId = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).getOffice().getDepartment().getCompany().getId();
        List<User> users = Lists.newArrayList(userRepository.findAll()).stream().filter(x -> x.getOffice().getDepartment().getCompany().getId().equals(companyId)).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }

    @Override
    public List<UserListElementDto> getAllWarehousemen(Long id) {
        List<User> users = Lists.newArrayList(userRepository.findAll()).stream().filter(x -> x.getOffice().getId().equals(id) && x.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()).contains("WAREHOUSEMAN")).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }

        }
        return usersDto;
    }

    @Override
    public UserRolesDto getLoggedUserRoles(String name) throws AppBaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return UserConverter.toUserRoles(user);
    }

    @Override
    public UserRolesDto getUserRoles(Long id) throws AppBaseException {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return UserConverter.toUserRoles(user);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public void deleteUser(Long id) throws AppBaseException {
        userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setEnabled(false);
    }

    @Override
    public PasswordInfoForAdmin getPasswordForAdmin(Long id) throws AppBaseException {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return PasswordInfoForAdmin.builder().userVersion(user.getVersion()).id(id).build();
    }

    @Override
    public void updatePasswordForAdmin(PasswordInfoForAdmin passwordInfoForAdmin) throws AppBaseException {
        User user = userRepository.findById(passwordInfoForAdmin.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        user.setPassword(passwordEncoder.encode(passwordInfoForAdmin.getNewPassword()));
        user.setVersion(passwordInfoForAdmin.getUserVersion());
        userRepository.save(user);

    }


    @Override
    public PasswordInfoDto getPassword(String username) throws AppBaseException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return PasswordInfoDto.builder().userVersion(user.getVersion()).build();
    }

    @Override
    public void updatePassword(PasswordInfoDto passwordInfoDto, String username) throws AppBaseException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));

        if (!(passwordInfoDto.getNewPassword().length() >= 8 && passwordInfoDto.getOldPassword().length() >= 8)) {
            throw new ServiceException(ServiceException.INCORRECT_LENGTH_PASSWORD);
        }
        if (!passwordEncoder.matches(passwordInfoDto.getOldPassword(), user.getPassword())) {
            throw new ServiceException(ServiceException.INCORRECT_PASSWORD);
        }
        if (!passwordEncoder.matches(passwordInfoDto.getNewPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordInfoDto.getNewPassword()));
            user.setVersion(passwordInfoDto.getUserVersion());
            userRepository.save(user);

        } else {
            throw new ServiceException(ServiceException.SAME_PASSWORD);
        }

    }

    @Override
    public void resetPassword(String username) throws AppBaseException {
        User user=userRepository.findByUsername(username).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String password = passwordGenerator.generate(8);
        emailService.sendSimpleMessage(user.getUserdata().getEmail(),"Password reset",password);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


}
