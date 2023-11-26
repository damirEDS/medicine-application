package com.medicineservices.springboot.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class MainController {
    @GetMapping("/")
    public String baseTemplate(){
        return "home";
    }

    @GetMapping("/about")
    public String aboutTemplate(){
        return "about";
    }

    @GetMapping("/main")
    public String unsecuredData() {
        return "unsecured";}

    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}