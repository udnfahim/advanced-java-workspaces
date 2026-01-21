package com.caffeine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentHostController {

        @GetMapping("/student")
        public String student() {
            return "index";
        }
}
