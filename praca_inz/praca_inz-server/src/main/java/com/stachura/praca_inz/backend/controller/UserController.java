package com.stachura.praca_inz.backend.controller;


import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.LoggedUserDto;
import com.stachura.praca_inz.backend.web.dto.ProfileInfoDto;
import com.stachura.praca_inz.backend.web.dto.UserListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ProfileInfoDto getProfile() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           return userService.getProfile(auth.getName());
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    LoggedUserDto getLoggeduser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getLoggedUser(auth.getName());
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody
//    Company getCompanyById(@PathVariable Long id) {
//           return userService.getCompanyById(id);
//    }
//
//    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody
//    Company getCompanyById(@RequestParam String name) {
//        return userService.getCompanyById(name);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseEntity<?> createNewCompany(@RequestBody Company company) {
//        userService.createNewCompany(company);
//        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(CompanyController.class).getCompanyById(company.getId()));
//        headers.setLocation(linkBuilder.toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void updateCompany(@RequestBody Company company) {
//        userService.updateCompany(company);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void deleteCompanyById(@PathVariable Long id) {
//        userService.deleteCompanyById(id);
//    }

}
