package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.security.UserRole;

import java.util.List;

public interface UserRoleRepository {

    UserRole find(String name);

    UserRole find(Long id);

    List<UserRole> findAll();

    void create(UserRole userRole)throws EntityException;

    void update(UserRole userRole)throws EntityException;

    void remove(Long id);

    void remove(UserRole userRole);
}
