package com.medicineservices.springboot.main.controllers;

import com.medicineservices.springboot.main.entities.Patient;
import com.medicineservices.springboot.main.service.PatientService;
import com.medicineservices.springboot.security.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("/med")
public class MainController {

    private final AuthService authService;
    private final PatientService patientService;

    public MainController(AuthService authService, PatientService patientService) {
        this.authService = authService;
        this.patientService = patientService;
    }
    @ModelAttribute
    public void addCommonAttributes(Model model) {
        String iin = authService.getCurrentUsername();
        Optional<Patient> patient = patientService.findByIin(iin);
        // Проверка, что информация о пациенте получена
        // Передача данных о пациенте на страницу
        model.addAttribute("fullName", patient.get().getFullName());
        model.addAttribute("email", patient.get().getEmail());
        // Другие общие атрибуты, если есть
    }
    @GetMapping("/")
    public String baseTemplate() {
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