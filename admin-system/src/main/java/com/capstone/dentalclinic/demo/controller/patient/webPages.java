package com.capstone.dentalclinic.demo.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class webPages {
    
    @GetMapping("/")
    public String landingPage() {
        return "PatientWebPages/index";
    }

    @GetMapping("/login")
    public String patientLoginPage() {
        return "PatientWebPages/PatientLoginPage";
    }

    @GetMapping("/patient-registration")
    public String patientRegistrationPage() {
        return "PatientWebPages/PatientRegistrationPage";
    }
}
