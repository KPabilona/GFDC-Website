package com.capstone.dentalclinic.demo.controller.patient;

import com.capstone.dentalclinic.demo.model.patient.Patient;
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
    public ModelAndView patientDashboard(Principal principal) {

        System.out.println("The Principal is " + principal.getName());
//        System.out.println("THE INFORMATION IS " + patientService.findByEmailAddress(principal.getName()));

        ModelAndView mav = new ModelAndView("/PatientWebPages/PatientDashboard");
        Patient patient = patientService.findByEmailAddress(principal.getName());
        mav.addObject("data", patient);

        return mav;
    }

}
