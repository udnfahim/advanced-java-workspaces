package com.springsecurity.controller;

import com.springsecurity.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final JwtService jwtService;

    public DashboardController( JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication,
                            @CookieValue(name = "JWT", required = false) String token) {

        if (authentication != null) {

            model.addAttribute("username", authentication.getName());

            model.addAttribute("roles", authentication.getAuthorities());

            if (token != null) {

                long expiresInMillis = jwtService.parseToken(token).getExpiration().getTime() - System.currentTimeMillis();

                long expiresInSeconds = expiresInMillis / 1000;

                Boolean rememberMe = jwtService.parseToken(token).get("rememberMe", Boolean.class);

                model.addAttribute("rememberMe", rememberMe != null && rememberMe);
                model.addAttribute("expiresIn", expiresInSeconds);

            }
            else {
                model.addAttribute("rememberMe", false);
            }
        }
        return "dashboard";
    }
}
