package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {


    User find(String name);

    User find(Long id);

    List<User> findAll();

    void create(User user)throws EntityException;

    void update(User user)throws EntityException;

    void remove(Long id);

    void remove(User user);
    
    
}
