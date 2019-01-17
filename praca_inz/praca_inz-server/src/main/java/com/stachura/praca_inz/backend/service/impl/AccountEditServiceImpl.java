package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.repository.*;
import com.stachura.praca_inz.backend.service.AccountEditService;
import com.stachura.praca_inz.backend.web.dto.user.ProfileEditDto;
import com.stachura.praca_inz.backend.web.dto.user.UserEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEditServiceImpl implements AccountEditService {

@Autowired
private UserRepository userRepository;

@Autowired
private OfficeRepository officeRepository;

@Autowired
private UserRoleRepository userRoleRepository;

@Autowired
private UserdataRepository userdataRepository;

@Autowired
private WarehouseRepository warehouseRepository;





    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_ADMIN')")
    public void updateAccountbyAdmin(UserEditDto userEditDto) throws EntityNotInDatabaseException {
        User user = userRepository.findById(userEditDto.getId()).orElseThrow((()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        user.setUsername(userEditDto.getUsername());
        user.setOffice(officeRepository.findById(userEditDto.getOfficeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setVersion(userEditDto.getVersionUser());

        if (userEditDto.getRoles() != null) {
            List<UserRole> userRoles = Lists.newArrayList(userRoleRepository.findAll()).stream().filter(x -> userEditDto.getRoles().stream().anyMatch(name -> name.equals(x.getName())))
                    .collect(Collectors.toList());

            user.setUserRoles(userRoles);
        }
        Userdata userdata = userdataRepository.findById(user.getUserdata().getId()).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        userdata.setEmail(userEditDto.getEmail());
        userdata.setSurname(userEditDto.getSurname());
        userdata.setPosition(userEditDto.getPosition());
        userdata.setName(userEditDto.getName());
        userdata.setWorkplace(userEditDto.getWorkplace());
        userdata.setDateOfJoin(Calendar.getInstance());
        Address address = new Address();
        address.setFlatNumber(userEditDto.getFlatNumber());
        address.setBuildingNumber(userEditDto.getHouseNumber());
        address.setStreet(userEditDto.getStreet());
        address.setCity(userEditDto.getCity());
        userdata.setAddress(address);
        userdata.setLanguage(userEditDto.getLanguage());
        userdata.setVersion(userEditDto.getVersionUserdata());

        Warehouse warehouse = user.getWarehouses().stream().filter(x->x.getWarehouseType().equals(WarehouseType.USER)).findFirst().orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        warehouse.setOffice(officeRepository.findById(userEditDto.getOfficeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        warehouse.setWarehouseType(WarehouseType.USER);
        warehouse.setVersion(userEditDto.getVersionWarehouse());
        userdataRepository.save(userdata);
        user.setUserdata(userdata);
        userRepository.save(user);
        warehouse.setUser(user);
        warehouse.setName(user.getUserdata().getName() + "|" + user.getUserdata().getSurname() + "|" + user.getUsername() + "|WAREHOUSE");
        warehouseRepository.save(warehouse);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_USER')")
    public void updateProfileByUser(ProfileEditDto profileEditDto) throws EntityNotInDatabaseException {
        User user = userRepository.findById(profileEditDto.getId()).orElseThrow((()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        user.setUsername(profileEditDto.getUsername());
        user.setOffice(officeRepository.findById(profileEditDto.getOfficeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setVersion(profileEditDto.getVersionUser());

        Userdata userdata = userdataRepository.findById(user.getUserdata().getId()).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        userdata.setEmail(profileEditDto.getEmail());
        userdata.setSurname(profileEditDto.getSurname());
        userdata.setPosition(profileEditDto.getPosition());
        userdata.setName(profileEditDto.getName());
        userdata.setWorkplace(profileEditDto.getWorkplace());
        userdata.setDateOfJoin(Calendar.getInstance());
        Address address = new Address();
        address.setFlatNumber(profileEditDto.getFlatNumber());
        address.setBuildingNumber(profileEditDto.getHouseNumber());
        address.setStreet(profileEditDto.getStreet());
        address.setCity(profileEditDto.getCity());
        userdata.setAddress(address);
        userdata.setLanguage(profileEditDto.getLanguage());
        userdata.setVersion(profileEditDto.getVersionUserdata());

        Warehouse warehouse = user.getWarehouses().stream().filter(x->x.getWarehouseType().equals(WarehouseType.USER)).findFirst().orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        warehouse.setOffice(officeRepository.findById(profileEditDto.getOfficeId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        warehouse.setWarehouseType(WarehouseType.USER);
        warehouse.setVersion(profileEditDto.getVersionWarehouse());
        try{
        userdataRepository.saveAndFlush(userdata);
        user.setUserdata(userdata);
        userRepository.saveAndFlush(user);
        warehouse.setUser(user);
        warehouse.setName(user.getUserdata().getName() + "|" + user.getUserdata().getSurname() + "|" + user.getUsername() + "|WAREHOUSE");
        warehouseRepository.saveAndFlush(warehouse);
        }catch (OptimisticLockException e){
            new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
    }
}
