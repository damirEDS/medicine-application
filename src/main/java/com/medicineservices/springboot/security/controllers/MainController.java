package com.medicineservices.springboot.security.controllers;

import com.medicineservices.springboot.security.service.AuthService;
import com.medicineservices.springboot.translation.service.TranslationService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class MainController {
    private final TranslationService translationService;

    public MainController(TranslationService translationService) {
        this.translationService= translationService;
    }
    @GetMapping("/main")
    public String unsecuredData() {
        String translatedMessage = translationService.getTranslation("auth.badCredentials",  LocaleContextHolder.getLocale());
        return translatedMessage;}

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