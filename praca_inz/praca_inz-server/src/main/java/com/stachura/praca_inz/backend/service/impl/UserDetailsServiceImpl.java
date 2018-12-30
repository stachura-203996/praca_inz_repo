package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.find(username);
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException(username);
    }
}
