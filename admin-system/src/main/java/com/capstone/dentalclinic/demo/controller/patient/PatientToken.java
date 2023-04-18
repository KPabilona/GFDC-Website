package com.capstone.dentalclinic.demo.controller.patient;


import com.capstone.dentalclinic.demo.services.patient.PatientTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientToken {

    private PatientTokenService patientTokenService;
    
    // This will handle the confirmation of the patient.
    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("tokens") String tokens, Model model) {

        String token = patientTokenService.patientConfirmationToken(tokens);

        if (token.equalsIgnoreCase("token/AlreadyConfirmedToken")) {
            model.addAttribute("errorMessage", patientTokenService.getConfirmedAt(token));
            return token;
        }
        return token;
    }
}
