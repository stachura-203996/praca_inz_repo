package com.stachura.praca_inz.backend.controller.registration;

import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.service.IRegistrationService;
import com.stachura.praca_inz.backend.web.dto.RegistrationDto;
import com.stachura.praca_inz.backend.web.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public class RegistrationController {

    @Autowired
    private IRegistrationService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid final RegistrationDto accountDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user user with information: {}", accountDto);

        final User registered = userService.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
