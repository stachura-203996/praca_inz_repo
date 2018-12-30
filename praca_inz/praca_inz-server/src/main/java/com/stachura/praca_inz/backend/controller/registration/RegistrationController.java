package com.stachura.praca_inz.backend.controller.registration;

import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.service.RegistrationService;
import com.stachura.praca_inz.backend.web.dto.RegisterResponse;
import com.stachura.praca_inz.backend.web.dto.RegistrationDto;
import com.stachura.praca_inz.backend.web.utils.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/secured/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /**
     * Metoda rejestrująca konto nowego użytkownika
     *
     * @param data obiekt nowego użytkownika
     * @return odpowiedź z informacją o powodzeniu operacji
     */
    @RequestMapping(value = "/self", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @PermitAll
    public ResponseEntity<?> register(@RequestBody RegistrationDto data) {
//        try {
            registrationService.registerNewUserAccount(data, false);
//            mailService.sendRegisterMessage(data.getEmail());
//        } catch (ServiceException e) {
//            return jsonResponse(new RegisterResponse(e.getMessage()));
//        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Metoda rejestrująca konto nowego użytkownika przez administratora
     *
     * @param data obiekt nowego użytkownika
     * @return odpowiedź z informacją o powodzeniu operacji
     */
    @RequestMapping(value = "/admin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?>  addUser(@RequestBody RegistrationDto data) {
//        try {
            registrationService.registerNewUserAccount(data, true);
//        } catch (ServiceException e) {
//            return jsonResponse(new RegisterResponse(e.getMessage()));
//        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
