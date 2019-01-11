package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.UserAlreadyExistException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.repository.*;
import com.stachura.praca_inz.backend.service.RegistrationService;
import com.stachura.praca_inz.backend.web.dto.user.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserdataRepository userdataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerNewUserAccount(final RegistrationDto data, boolean verified) throws ServiceException {
//        if (emailExist(data.getEmail())) {
//            throw new UserAlreadyExistException("There is an user with that email adress: " + data.getEmail());
//        }

        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEnabled(true);
        user.setAccountExpired(false);
        user.setOffice(officeRepository.findById(data.getOfficeId()).orElseThrow(() -> new ServiceException()));
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);

        if (data.getRoles() != null) {
            List<UserRole> userRoles = Lists.newArrayList(userRoleRepository.findAll()).stream().filter(x -> data.getRoles().stream().anyMatch(name -> name.equals(x.getName())))
                    .collect(Collectors.toList());

            user.setUserRoles(userRoles);
        }
        Userdata userdata = new Userdata();
        userdata.setEmail(data.getEmail());
        userdata.setSurname(data.getSurname());
        userdata.setPosition(data.getPosition());
        userdata.setName(data.getName());
        userdata.setWorkplace(data.getWorkplace());
        Address address = new Address();
        address.setFlatNumber(data.getFlatNumber());
        address.setBuildingNumber(data.getHouseNumber());
        address.setStreet(data.getStreet());
        address.setCity(data.getCity());
        userdata.setAddress(address);

        Warehouse warehouse = new Warehouse();
        warehouse.setName(data.getUsername());
        warehouse.setOffice(officeRepository.findById(data.getOfficeId()).orElseThrow(() -> new ServiceException()));
        warehouse.setWarehouseType(WarehouseType.USER);

        warehouse.setDeleted(false);


        userdataRepository.save(userdata);
        user.setUserdata(userdata);
        userRepository.save(user);
        warehouse.setUser(user);
        warehouseRepository.save(warehouse);

    }

//    private boolean emailExist(final String email) {
//        return userdataRepository.findByEmail(email) != null;
//    }
}
