package com.capstone.dentalclinic.demo.controller.patient;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientRegistrationPage {

    @GetMapping("/registration")
    public String patientRegistrationPage() {
        return "PatientWebPages/PatientRegistrationPage";
    }
}
