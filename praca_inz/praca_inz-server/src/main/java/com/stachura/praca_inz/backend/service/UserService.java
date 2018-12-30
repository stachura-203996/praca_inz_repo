package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.web.dto.LoggedUserDto;
import com.stachura.praca_inz.backend.web.dto.ProfileInfoDto;
import com.stachura.praca_inz.backend.web.dto.UserInfoDto;
import com.stachura.praca_inz.backend.web.dto.UserListElementDto;

import java.util.List;

public interface UserService {
    /**
     * Metoda zwracająca wszystkich użytkowniów jako obiekty typu {@link UserListElementDto}
     *
     * @return lista użytkowników
     */
    List<UserListElementDto> getAllUsers();

    /**
     * Metoda zwracająca użytkownika o danym loginie jako obiekty typu {@link ProfileInfoDto}
     *
     * @return
     */
    ProfileInfoDto getProfile(String name);

    /**
     * Metoda zwracająca użytkownika o danym loginie jako obiekty typu {@link ProfileInfoDto}
     *
     * @return użytkownik
     */
    LoggedUserDto getLoggedUser(String name);

    /**
     * Metoda zwracająca użytkownika o danym loginie jako obiekty typu {@link UserInfoDto}
     *
     * @return użytkownik
     */
    UserInfoDto getUserInfo(String name);

    List<UserListElementDto> getAllUsersForManager(String username);

    List<UserListElementDto> getAllUsersForCompanyAdmin(String username);
}
