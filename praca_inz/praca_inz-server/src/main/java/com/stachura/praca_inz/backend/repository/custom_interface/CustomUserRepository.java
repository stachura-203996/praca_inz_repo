package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.security.User;

public interface CustomUserRepository {
    void detachUser(User entity);
}
