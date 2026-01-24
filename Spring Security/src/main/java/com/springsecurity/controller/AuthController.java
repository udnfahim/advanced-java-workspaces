package com.springsecurity.controller;

import com.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
}
