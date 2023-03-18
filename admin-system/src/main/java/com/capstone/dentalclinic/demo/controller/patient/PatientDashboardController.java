package com.capstone.dentalclinic.demo.controller.patient;

import lombok.AllArgsConstructor;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientDashboardController {

    @GetMapping("/dashboard")
    public String patientDashboard(Authentication authentication) {
        System.out.println("This is Authentication of the user");
        System.out.println(authentication.getDetails());
        
        return "/PatientWebPages/PatientDashboard";
    }


}
