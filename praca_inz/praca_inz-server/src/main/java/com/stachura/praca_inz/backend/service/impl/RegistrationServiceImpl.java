package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.UserAlreadyExistException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.UserdataRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.RegistrationService;
import com.stachura.praca_inz.backend.web.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserdataRepository userdataRepository;

    @Autowired
    private UserRepository userRepository;

    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerNewUserAccount(final RegistrationDto data,boolean verified) {
        if (emailExist(data.getEmail())) {
            throw new UserAlreadyExistException("There is an user with that email adress: " + data.getEmail());
        }
        final User user = new User();
        final Userdata userdata =new Userdata();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userdata.setEmail(data.getEmail());
        userdata.setSurname(data.getSurname());
        user.setUserdata(userdata);


//        userdata.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        try {
             userRepository.create(user);
        } catch (EntityException e) {
            e.printStackTrace();
        }
    }
    private boolean emailExist(final String email) {
        return userdataRepository.findByEmail(email) != null;
    }
}
