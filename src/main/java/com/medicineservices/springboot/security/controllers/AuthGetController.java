package com.medicineservices.springboot.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthGetController {
    @GetMapping("/registration")
    public String registrationTemplate(){
        return "registration";}

    @GetMapping("/auth")
    public String authTemplate(){
        return "auth";
    }
}
