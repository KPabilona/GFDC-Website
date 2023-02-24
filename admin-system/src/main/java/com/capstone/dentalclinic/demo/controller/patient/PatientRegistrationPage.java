package com.capstone.dentalclinic.demo.controller.patient;


import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import com.capstone.dentalclinic.demo.services.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientRegistrationPage {

    private PatientService patientService;

    @GetMapping("/registration")
    public String patientRegistrationPage(Model model) {
        model.addAttribute("patient", new PatientDTO());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("maritalStatus", MaritalStatus.values());
        return "PatientWebPages/PatientRegistrationPage";
    }

    @PostMapping("/registration")
    public String patientSubmissionForm(@ModelAttribute("patient") @Valid PatientDTO patientDTO,
                                        BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("isMatchedPassword", patientService.isMatchedPassword(patientDTO));
            if(patientService.patientEmailAlreadyExist(patientDTO.getEmailAddress())){
                model.addAttribute("isEmailExists", "Email Already Exists, Try Another One.");
                return "PatientWebPages/PatientRegistrationPage";
            }
            model.addAttribute("genders", Gender.values());
            model.addAttribute("maritalStatus", MaritalStatus.values());
            return "PatientWebPages/PatientRegistrationPage";
        }

        return "";
    }
}
