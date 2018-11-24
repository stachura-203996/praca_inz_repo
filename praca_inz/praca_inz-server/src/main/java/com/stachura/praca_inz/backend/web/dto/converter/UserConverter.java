package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.web.dto.*;

import java.util.ArrayList;

public class UserConverter {

    /**
     * Metoda konwertująca encję {@link User} na obiekt {@link LoggedUserDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami o zalogowanym użytkowniku
     */
    public static LoggedUserDto toLogggedUser(User user) {
        return LoggedUserDto.builder()
                .username(user.getUsername())
                .firstName(user.getUserdata().getName())
                .lastName(user.getUserdata().getSurname())
                .roles(new ArrayList<>())
                .build();
    }

    /**
     * Metoda konwertująca encję {@link User} na obiekt {@link UserListElementDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami o użytkowniku
     */
    public static UserListElementDto toUserListElement(User user) {
        return UserListElementDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .email(user.getUserdata().getEmail())
                .locked(user.isAccountLocked())
                .verified(user.isEnabled())
                .build();
    }

    /**
     * Metoda konwertująca encję {@link User} na obiekt {@link UserEditDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami o użytkowniku
     */
    public static UserEditDto toUserEdit(User user) {
        return UserEditDto.builder()
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .city(user.getUserdata().getAddress().getCity())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNo(Integer.parseInt(user.getUserdata().getAddress().getBuildingNumber()))
                .flatNo(Integer.valueOf(user.getUserdata().getAddress().getFlatNumber()))
//     TODO           .userdataVersion(user.getUserdata().getVersion())
                .build();
    }

   /* *//**
     * Metoda konwertująca encję {@link Account} na obiekt {@link UserEditDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami o użytkowniku
     *//*
    public static UserEditByAdminDto toUserEditByAdminView(User user) {
        UserEditByAdminDto userEdit = UserEditByAdminDto.builder()
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .city(user.getUserdata().getAddress().getCity())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNo(Integer.parseInt(user.getUserdata().getAddress().getBuildingNumber()))
                .flatNo(Integer.valueOf(user.getUserdata().getAddress().getFlatNumber()))
                .email(user.getUserdata().getEmail())
//     TODO           .userdataVersion(user.getUserdata().getVersion())
                .build();

        if (user.getUserdata().getSupervisor() != null && !user.getUserdata().getSupervisor().getAccount().getUsername().isEmpty()) {
            user.setSupervisorUsername(user.getUserdata().getSupervisor().getAccount().getUsername());
        }
        return user;
    }

    *//**
     * Metoda konwertująca encję {@link Account} na obiekt {@link ProfileInfo} przesyłany do widoku
     *
     * @param account encja konta
     * @return obiekt z informacjami o zalogowanym użytkowniku
     *//*
    public static ProfileInfo toProfileInfo(Account account) {
        return ProfileInfo.builder()
                .id(account.getId())
                .name(account.getUserdata().getName())
                .surname(account.getUserdata().getSurname())
                .city(account.getUserdata().getCity())
                .email(account.getUserdata().getEmail())
                .street(account.getUserdata().getStreet())
                .houseNo(account.getUserdata().getHouseNo())
                .flatNo(account.getUserdata().getFlatNo())
                .pointsSum(account.getUserdata().getPointsSum())
                .build();
    }

    *//**
     * Metoda konwertująca encję {@link Account} na obiekt {@link PasswordInfoForAdmin} przesyłany do widoku
     *
     * @param account encja konta
     * @return obiekt z informacjami o haśle użytkownika
     *//*
    public static PasswordInfoForAdmin toPasswordAdminView(Account account) {
        return PasswordInfoForAdmin.builder()
                .userdataVersion(account.getVersion())
                .build();
    }

    *//**
     * Tworzy obiekt encji {@link User} z obiektu po edycji hasła przez administratora
     *
     * @param newPasswordHash skrót nowego hasła
     * @param pass obiekt przesłany z widoku z informacją o haśle i polu wersji
     * @param account encja konta, którego hasło jest zmieniane
     * @return obiekt konta ze zmienionym hasłem
     *//*
    public static User toPasswordEdit(String newPasswordHash, PasswordInfoForAdmin pass, User account) {
        User userAccount = new User(account.getId(), pass.getUserdataVersion());
        setUserAccount(userAccount, account);

        userAccount.setPassword(newPasswordHash);
        return userAccount;
    }

    *//**
     * Metoda konwertująca encję {@link User} na obiekt {@link PasswordInfoDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami o haśle użytkownika
     *//*
    public static PasswordInfoDto toPasswordView(User user) {
        return PasswordInfoDto.builder()
                .userdataVersion(user.getVersion())
                .build();
    }

    *//**
     * Tworzy obiekt encji {@link User} z obiektu po edycji hasła przez administratora
     *
     * @param newPasswordHash skrót nowego hasła
     * @param pass obiekt przesłany z widoku z informacją o haśle i polu wersji
     * @param user encja konta, którego hasło jest zmieniane
     * @return obiekt konta ze zmienionym hasłem
     *//*
    public static User toPasswordEdit(String newPasswordHash, PasswordInfoDto pass, User user) {
        User userAccount = new User(user.getId(), pass.getUserdataVersion());
        setUserAccount(userAccount, user);

        userAccount.setPassword(newPasswordHash);
        return userAccount;
    }*/

// TODO   /**
//     * Przepisuje informacje, które nie podlegają edycji do encji {@link User} przesyłanej do bazy
//     *
//     * @param userAccount encja konta przesyłana do bazy
//     * @param account encja utworzona po edycji konta
//     */
//    private static void setUserAccount(User userAccount, User account) {
//        userAccount.setAccountLevelCollection(account.getAccountLevelCollection());
//        userAccount.setEnabled(account.isEnabled());
//        userAccount.setPerformanceReportCollection(account.getPerformanceReportCollection());
//        userAccount.setPointCollection(account.getPointCollection());
//        userAccount.setPurchaseCollection(account.getPurchaseCollection());
//        userAccount.setUserdata(account.getUserdata());
//        userAccount.setUsername(account.getUsername());
//        userAccount.setVerified(account.getVerified());
//    }
}
