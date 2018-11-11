package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.UserRepository;
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
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException(username);
    }
    //TODO Może trzeba dodać tutaj tworzenie nowego User z wypełnionymi zmiennymi

//    @Override
//    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
//        final String ip = getClientIP();
//        if (loginAttemptService.isBlocked(ip)) {
//            throw new RuntimeException("blocked");
//        }
//
//        try {
//            final User user = userRepository.findByEmail(email);
//            if (user == null) {
//                throw new UsernameNotFoundException("No user found with username: " + email);
//            }
//
//            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
//        } catch (final Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

}
