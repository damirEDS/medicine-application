package com.medicineservices.springboot.translation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {

    @GetMapping("/")
    public String langTemplate(){
        return "lang";
    }
}
