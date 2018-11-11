package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.error.UserAlreadyExistException;
import com.stachura.praca_inz.backend.model.Employee;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.EmployeeRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.IRegistrationService;
import com.stachura.praca_inz.backend.web.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationService implements IRegistrationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(final RegistrationDto accountDto) {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an user with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();
        final Employee employee =new Employee();
        user.setUsername(accountDto.getFirstName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        employee.setEmail(accountDto.getEmail());
        employee.setSurname(accountDto.getLastName());
        employee.setUser(user);


//        employee.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }
    private boolean emailExist(final String email) {
        return employeeRepository.findByEmail(email) != null;
    }
}
