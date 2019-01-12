package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.web.dto.user.*;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class UserConverter {


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

    public static ProfileInfoDto toProfileInfo(User user) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ProfileInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .position(user.getUserdata().getPosition())
                .company(user.getOffice().getDepartment().getCompany().getName())
                .department(user.getOffice().getDepartment().getName())
                .office(user.getOffice().getName())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .dateOfJoining(formatter.format(user.getUserdata().getDateOfJoin().getTime()))
                .city(user.getUserdata().getAddress().getCity())
                .email(user.getUserdata().getEmail())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNumber(user.getUserdata().getAddress().getBuildingNumber())
                .flatNumber(user.getUserdata().getAddress().getFlatNumber())
                .roles(user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                .workplace(user.getUserdata().getWorkplace())
                .build();
    }

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
                .admin(user.getUserRoles().stream().filter(x -> x.getName().equals("ADMIN")).findFirst().isPresent())
                .company_admin(user.getUserRoles().stream().filter(x -> x.getName().equals("COMPANY_ADMIN")).findFirst().isPresent())
                .manager(user.getUserRoles().stream().filter(x -> x.getName().equals("MANAGER")).findFirst().isPresent())
                .warehouseman(user.getUserRoles().stream().filter(x -> x.getName().equals("WAREHOUSEMAN")).findFirst().isPresent())
                .user(user.getUserRoles().stream().filter(x -> x.getName().equals("USER")).findFirst().isPresent())
                .build();
    }

    public static UserEditDto toUserEditDto(User user, Warehouse warehouse) {
        return UserEditDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .position(user.getUserdata().getPosition())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .city(user.getUserdata().getAddress().getCity())
                .email(user.getUserdata().getEmail())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNumber(user.getUserdata().getAddress().getBuildingNumber())
                .flatNumber(user.getUserdata().getAddress().getFlatNumber())
                .roles(user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                .workplace(user.getUserdata().getWorkplace())
                .officeId(user.getOffice().getId())
                .position(user.getUserdata().getPosition())
                .versionUser(user.getVersion())
                .versionUserdata(user.getUserdata().getVersion())
                .versionWarehouse(warehouse.getVersion())
                .build();
    }

    public static ProfileEditDto toProfileEditDto(User user,Warehouse warehouse) {
        return ProfileEditDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .position(user.getUserdata().getPosition())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .city(user.getUserdata().getAddress().getCity())
                .email(user.getUserdata().getEmail())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNumber(user.getUserdata().getAddress().getBuildingNumber())
                .flatNumber(user.getUserdata().getAddress().getFlatNumber())
                .workplace(user.getUserdata().getWorkplace())
                .officeId(user.getOffice().getId())
                .position(user.getUserdata().getPosition())
                .versionUser(user.getVersion())
                .versionUserdata(user.getUserdata().getVersion())
                .versionWarehouse(warehouse.getVersion())
                .build();
    }

    public static UserInfoDto toUserInfo(User user) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
                .dateOfJoining(formatter.format(user.getUserdata().getDateOfJoin().getTime()))
                .street(user.getUserdata().getAddress().getStreet())
                .houseNumber(user.getUserdata().getAddress().getBuildingNumber())
                .flatNumber(user.getUserdata().getAddress().getFlatNumber())
                .roles(user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                .build();
    }
}
