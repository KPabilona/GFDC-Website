package com.capstone.dentalclinic.demo.controller.patient;


import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.services.patient.PatientServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientLoginPage {
    private final PatientServicesImpl patientServices;

    @GetMapping("/login")
    public String patientLoginPage(Model model) {
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
