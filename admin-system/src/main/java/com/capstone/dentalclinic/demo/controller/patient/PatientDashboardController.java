package com.capstone.dentalclinic.demo.controller.patient;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.services.patient.PatientService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientDashboardController {
    
    private final PatientService patientService;

    @GetMapping("/dashboard")
    public ModelAndView patientDashboard(Principal principal) {
        ModelAndView mav = new ModelAndView("/PatientWebPages/PatientDashboard");
        Patient patient = patientService.findByEmailAddress(principal.getName());
        mav.addObject("services", Services.values());
        mav.addObject("data", patient);
        
        return mav;
    }

}
