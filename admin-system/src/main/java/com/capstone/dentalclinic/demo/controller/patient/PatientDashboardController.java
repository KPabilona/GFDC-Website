package com.capstone.dentalclinic.demo.controller.patient;

import com.capstone.dentalclinic.demo.services.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientDashboardController {

//    private final PatientRepository patientRepository;

    private final PatientService patientService;
    
    @GetMapping("/dashboard")
    public String patientDashboard(Principal principal) {
        System.out.println("This is Authentication of the user");
        System.out.println(principal.getName());
        System.out.println("This is the Output from the email that being recived! " + patientService.findByEmailAddress(principal.getName()));

        ModelAndView mav = new ModelAndView();
        mav.addObject("user-data", patientService.findByEmailAddress(principal.getName()));

        return "/PatientWebPages/PatientDashboard";
    }

}
