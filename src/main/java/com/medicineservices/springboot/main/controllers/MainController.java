package com.medicineservices.springboot.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/med")
public class MainController {
    @GetMapping("/")
    public String baseTemplate(){
        return "home";
    }

    @GetMapping("/about")
    public String aboutTemplate(){
        return "about";
    }

    @GetMapping("/contact")
    public String contactTemplate(){
        return "contact";
    }

    @GetMapping("/profile")
    public String profileTemplate(){
        return "profile";
    }

    @GetMapping("/appointment")
    public String appointmentTemplate(){return "appointment";}

    @GetMapping("/settings")
    public String settingsTemplate(){
        return "settings";
    }

    @GetMapping("/language")
    public String langugeTemplate(){
        return "language";
    }

    @GetMapping("/history")
    public String historyTemplate(){
        return "history";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}