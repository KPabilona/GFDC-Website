package com.capstone.dentalclinic.demo.controller.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientForgotPasswordController {

    @GetMapping("/forgotpassword")
    public String getForgotPasswordPage() {
        return "";
    }

}
