package com.quiz4.controller;

//import com.quiz4.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrontendController {

//    private final JwtService jwtService;
//
//    public FrontendController(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }

    @GetMapping({"/index", "/order"})
    public String dashboard() {
        return "index"; // return dashboard template
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @PostMapping("/auth/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        HttpServletResponse response) {
//
//        String role = username.equals("admin") ? "ADMIN" : "USER";
//        String token = jwtService.generateToken(username, role);
//
//        Cookie cookie = new Cookie("JWT", token);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        return "redirect:/index";
//    }
}
