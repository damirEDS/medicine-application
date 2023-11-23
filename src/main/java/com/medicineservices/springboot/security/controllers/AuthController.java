package com.medicineservices.springboot.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.medicineservices.springboot.security.dtos.JwtRequest;
import com.medicineservices.springboot.security.dtos.RegistrationUserDto;
import com.medicineservices.springboot.security.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth")
    public String showLoginForm(Model model) {
        model.addAttribute("authRequest", new JwtRequest());
        return "login"; // This will return the login2.html Thymeleaf template
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationUserDto", new RegistrationUserDto());
        return "register"; // This will return the register.html Thymeleaf template
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}