package com.stachura.praca_inz.backend.controller;


import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.UserListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserListElementDto> getAll() {
        return userService.getAllUsers();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody
//    Company get(@PathVariable Long id) {
//        return userService.get(id);
//    }
//
//    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody
//    Company get(@RequestParam String name) {
//        return userService.get(name);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseEntity<?> create(@RequestBody Company company) {
//        userService.create(company);
//        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(CompanyController.class).get(company.getId()));
//        headers.setLocation(linkBuilder.toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void update(@RequestBody Company company) {
//        userService.update(company);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void delete(@PathVariable Long id) {
//        userService.delete(id);
//    }

}
