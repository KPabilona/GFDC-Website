package com.capstone.dentalclinic.demo.controller.patient;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientDashboardController {

    @GetMapping("/dashboard")
    public String patientDashboard(Principal principal) {
        System.out.println("This is Authentication of the user");
        System.out.println(principal.getName());
        
        return "/PatientWebPages/PatientDashboard";
    }

}
