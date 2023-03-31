package com.capstone.dentalclinic.demo.controller.administrator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class LoginController {

// This will handle home page view of the page. 
    @GetMapping("/login")
    public String HomePageView() {
        return "admin/Login";
    }
    
// This will handle home page view of the page with error message 
// if ever the receptionist entered invalid credentials. 
    @GetMapping("/login-error")
    public String LoginErrorPage(Model model) {
        model.addAttribute("errorMessage", true);
        return "admin/Login";
    }
}
