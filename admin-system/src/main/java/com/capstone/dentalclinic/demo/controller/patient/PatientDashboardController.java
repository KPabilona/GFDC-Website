package com.capstone.dentalclinic.demo.controller.patient;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;

import java.security.Principal;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientDashboardController {

    private final PatientRepository patientRepository;
    
    @GetMapping("/dashboard")
    public String patientDashboard(Principal principal, Model model) {
        System.out.println("This is Authentication of the user");
        System.out.println(principal.getName());
        System.out.println("This is the Output from the email that being recived! " + patientRepository.findByPatientEmailAddress(principal.getName()));
        model.addAttribute("user-login-data", patientRepository.findByPatientEmailAddress(principal.getName().toString()));
    
        return "/PatientWebPages/PatientDashboard";
    }

}
