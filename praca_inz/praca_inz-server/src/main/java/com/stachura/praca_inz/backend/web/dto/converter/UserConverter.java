package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.web.dto.user.*;

import java.util.stream.Collectors;

public class UserConverter {

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
                .roles(user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                .build();
    }




    /**
     * Metoda konwertująca encję {@link User} na obiekt {@link ProfileInfoDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami do profilu zalogowanego użytkownika
     */
    public static ProfileInfoDto toProfileInfo(User user) {
        return ProfileInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .position(user.getUserdata().getPosition())
                .company(user.getOffice().getDepartment().getCompany().getName())
                .department(user.getOffice().getDepartment().getName())
                .office(user.getOffice().getName())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .city(user.getUserdata().getAddress().getCity())
                .email(user.getUserdata().getEmail())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNumber(user.getUserdata().getAddress().getBuildingNumber())
                .flatNumber(user.getUserdata().getAddress().getFlatNumber())
                .zipCode(user.getUserdata().getAddress().getZipCode())
                .roles(user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                .workplace(user.getUserdata().getWorkplace())
                .build();
    }

    /**
     * Metoda konwertująca encję {@link User} na obiekt {@link LoggedUserDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami o zalogowanym użytkowniku
     */
    public static LoggedUserDto toLoggedUser(User user) {
        return LoggedUserDto.builder()
                .username(user.getUsername())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .companyId(user.getOffice().getDepartment().getCompany().getId())
                .build();
    }


    public static UserRolesDto toUserRoles(User user) {
        return UserRolesDto.builder()
                .admin(user.getUserRoles().stream().filter(x->x.getName().equals("ADMIN")).findFirst().isPresent())
                .company_admin(user.getUserRoles().stream().filter(x->x.getName().equals("COMPANY_ADMIN")).findFirst().isPresent())
                .manager(user.getUserRoles().stream().filter(x->x.getName().equals("MANAGER")).findFirst().isPresent())
                .warehouseman(user.getUserRoles().stream().filter(x->x.getName().equals("WAREHOUSEMAN")).findFirst().isPresent())
                .user(user.getUserRoles().stream().filter(x->x.getName().equals("USER")).findFirst().isPresent())
                .build();
    }


    /**
     * Metoda konwertująca encję {@link User} na obiekt {@link LoggedUserDto} przesyłany do widoku
     *
     * @param user encja konta
     * @return obiekt z informacjami o zalogowanym użytkowniku
     */
    public static UserInfoDto toUserInfo(User user) {
        return UserInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .position(user.getUserdata().getPosition())
                .company(user.getOffice().getDepartment().getCompany().getName())
                .companyId(user.getOffice().getDepartment().getCompany().getId())
                .department(user.getOffice().getDepartment().getName())
                .departmentId(user.getOffice().getDepartment().getId())
                .office(user.getOffice().getName())
                .officeId(user.getOffice().getId())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .city(user.getUserdata().getAddress().getCity())
                .email(user.getUserdata().getEmail())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNumber(user.getUserdata().getAddress().getBuildingNumber())
                .flatNumber(user.getUserdata().getAddress().getFlatNumber())
                .zipCode(user.getUserdata().getAddress().getZipCode())
                .roles(user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                .build();
    }




//    /**
//     * Metoda konwertująca encję {@link User} na obiekt {@link UserEditDto} przesyłany do widoku
//     *
//     * @param user encja konta
//     * @return obiekt z informacjami o użytkowniku
//     */
//    public static UserEditDto toUserEdit(User user) {
//        return UserEditDto.builder()
//                .name(user.getCompanies().getName())
//                .surname(user.getCompanies().getSurname())
//                .city(user.getCompanies().getAddress().getCity())
//                .street(user.getCompanies().getAddress().getStreet())
//                .houseNo(Integer.parseInt(user.getCompanies().getAddress().getBuildingNumber()))
//                .flatNo(Integer.valueOf(user.getCompanies().getAddress().getFlatNumber()))
////     TODO           .userdataVersion(user.getCompanies().getVersion())
//                .build();
//    }

//    //**
//     * Metoda konwertująca encję {@link Account} na obiekt {@link UserEditDto} przesyłany do widoku
//     *
//     * @param user encja konta
//     * @return obiekt z informacjami o użytkowniku
//     *//*
//    public static UserEditByAdminDto toUserEditByAdminView(User user) {
//        UserEditByAdminDto userEdit = UserEditByAdminDto.builder()
//                .name(user.getCompanies().getName())
//                .surname(user.getCompanies().getSurname())
//                .city(user.getCompanies().getAddress().getCity())
//                .street(user.getCompanies().getAddress().getStreet())
//                .houseNo(Integer.parseInt(user.getCompanies().getAddress().getBuildingNumber()))
//                .flatNo(Integer.valueOf(user.getCompanies().getAddress().getFlatNumber()))
//                .email(user.getCompanies().getEmail())
////     TODO           .userdataVersion(user.getCompanies().getVersion())
//                .build();
//
//        if (user.getCompanies().getSupervisor() != null && !user.getCompanies().getSupervisor().getAccount().getUsername().isEmpty()) {
//            user.setSupervisorUsername(user.getCompanies().getSupervisor().getAccount().getUsername());
//        }
//        return user;
//    }

//    *//**
//     * Metoda konwertująca encję {@link Account} na obiekt {@link ProfileInfo} przesyłany do widoku
//     *
//     * @param account encja konta
//     * @return obiekt z informacjami o zalogowanym użytkowniku
//     *//*
//    public static ProfileInfo toProfileInfo(Account account) {
//        return ProfileInfo.builder()
//                .id(account.getId())
//                .name(account.getCompanies().getName())
//                .surname(account.getCompanies().getSurname())
//                .city(account.getCompanies().getCity())
//                .email(account.getCompanies().getEmail())
//                .street(account.getCompanies().getStreet())
//                .houseNo(account.getCompanies().getHouseNo())
//                .flatNo(account.getCompanies().getFlatNo())
//                .pointsSum(account.getCompanies().getPointsSum())
//                .build();
//    }

//    *//**
//     * Metoda konwertująca encję {@link Account} na obiekt {@link PasswordInfoForAdmin} przesyłany do widoku
//     *
//     * @param account encja konta
//     * @return obiekt z informacjami o haśle użytkownika
//     *//*
//    public static PasswordInfoForAdmin toPasswordAdminView(Account account) {
//        return PasswordInfoForAdmin.builder()
//                .userdataVersion(account.getVersion())
//                .build();
//    }
//
//    *//**
//     * Tworzy obiekt encji {@link User} z obiektu po edycji hasła przez administratora
//     *
//     * @param newPasswordHash skrót nowego hasła
//     * @param pass obiekt przesłany z widoku z informacją o haśle i polu wersji
//     * @param account encja konta, którego hasło jest zmieniane
//     * @return obiekt konta ze zmienionym hasłem
//     *//*
//    public static User toPasswordEdit(String newPasswordHash, PasswordInfoForAdmin pass, User account) {
//        User userAccount = new User(account.getId(), pass.getUserdataVersion());
//        setUserAccount(userAccount, account);
//
//        userAccount.setPassword(newPasswordHash);
//        return userAccount;
//    }


//    *//**
//     * Tworzy obiekt encji {@link User} z obiektu po edycji hasła przez administratora
//     *
//     * @param newPasswordHash skrót nowego hasła
//     * @param pass obiekt przesłany z widoku z informacją o haśle i polu wersji
//     * @param user encja konta, którego hasło jest zmieniane
//     * @return obiekt konta ze zmienionym hasłem
//     *//*
//    public static User toPasswordEdit(String newPasswordHash, PasswordInfoDto pass, User user) {
//        User userAccount = new User(user.getId(), pass.getUserdataVersion());
//        setUserAccount(userAccount, user);
//
//        userAccount.setPassword(newPasswordHash);
//        return userAccount;
//    }*/

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
//        userAccount.setUsers(account.getCompanies());
//        userAccount.setUsername(account.getUsername());
//        userAccount.setVerified(account.getVerified());
//    }
}
