package com.springsecurity.controller;

import com.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }
}
