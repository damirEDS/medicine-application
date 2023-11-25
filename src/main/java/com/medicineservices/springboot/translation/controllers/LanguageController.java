package com.medicineservices.springboot.translation.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
public class LanguageController {

    @PostMapping("/language")
    public void changeLanguage(@RequestParam String language, HttpServletRequest request) {
        request.getSession().setAttribute("LANGUAGE", new Locale(language));
    }
}
