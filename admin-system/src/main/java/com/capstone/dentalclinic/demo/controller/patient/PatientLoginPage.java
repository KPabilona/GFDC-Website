package com.capstone.dentalclinic.demo.controller.patient;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientLoginPage {

    @GetMapping("/login")
    public String patientLoginPage(Model model) {
//        model.addAttribute("successRegistration", true);
        return "PatientWebPages/PatientLoginPage";
    }

    @GetMapping("/login-error")
    public String errorPatientLoginPage(Model model){
        model.addAttribute("errorMessage", true);
        return "PatientWebPages/PatientLoginPage";
    }

    @GetMapping("login-success")
    public String successPatientLoginPage(Model model) {
        model.addAttribute("successRegistration", true);
        return "PatientWebPages/PatientLoginPage";
    }
}
