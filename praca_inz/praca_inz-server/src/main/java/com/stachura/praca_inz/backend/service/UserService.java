package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.web.dto.UserListElementDto;

import java.util.List;

public interface UserService {
    /**
     * Metoda zwracająca wszystkich użytkowniów jako obiekty typu {@link UserListElementDto}
     *
     * @return lista użytkowników
     */
    List<UserListElementDto> getAllUsers();
}
