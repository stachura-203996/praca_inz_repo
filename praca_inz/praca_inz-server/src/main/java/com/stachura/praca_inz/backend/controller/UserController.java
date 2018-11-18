package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@Controller
@RequestMapping("/login")
public class UserController {

//    @RequestMapping("/login")
//    public boolean login(@RequestBody User user) {
//        return user.getUsername().equals("user") && user.getPassword().equals("password");
//    }
//
//    @RequestMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization")
//                .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//                .decode(authToken)).split(":")[0];
//    }

//    @GetMapping
//    public String home(Model model) {
//        return "forward:/index.html";
//    }
}
