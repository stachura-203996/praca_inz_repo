package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {


    User find(String name);

    User find(Long id);

    List<User> findAll();

    void create(User user)throws EntityException;

    User update(User user)throws EntityException;

    void remove(Long id);

    void remove(User user);
    
    
}
