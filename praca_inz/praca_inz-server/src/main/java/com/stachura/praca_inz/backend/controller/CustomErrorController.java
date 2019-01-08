package com.stachura.praca_inz.backend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController  implements ErrorController {


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "index.html";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            } else if(statusCode == HttpStatus.BAD_REQUEST.value()){
                return "error-400";
            } else if(statusCode == HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value()){
                return "error-401";
            } else if(statusCode == HttpStatus.UNAUTHORIZED.value()){
                return "error-403";
            }

        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
