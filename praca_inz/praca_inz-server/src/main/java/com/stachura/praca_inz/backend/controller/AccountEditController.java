package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.service.AccountEditService;
import com.stachura.praca_inz.backend.web.dto.user.ProfileEditDto;
import com.stachura.praca_inz.backend.web.dto.user.UserEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secured/account/edit")
public class AccountEditController {


    @Autowired
    private AccountEditService accountEditService;

    @RequestMapping(value = "/admin", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> saveAccountAfterEdit(@RequestBody UserEditDto data) throws ServiceException {
        accountEditService.updateAccountbyAdmin(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/self", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> safeProfileAfterEdit(@RequestBody ProfileEditDto data)throws ServiceException {

        accountEditService.updateProfileByUser(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}