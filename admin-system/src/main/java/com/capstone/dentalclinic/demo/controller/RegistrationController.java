package com.capstone.dentalclinic.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.UserModel;

@Controller
@RequestMapping("/system")
public class RegistrationController {
    
    // This will handle the registration page view of the page.
    @GetMapping("/registration")
    public String RegistrationPageView(Model model) {
        model.addAttribute(new UserModel());
        model.addAttribute("genders", Gender.values());
        return "admin/Registration";
    }

    @PostMapping("/register")
    public String RegistrationSubmition() {
        return "";
    }
}
