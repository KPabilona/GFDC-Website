package com.capstone.dentalclinic.demo.controller.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/system/admin")
@AllArgsConstructor
public class LoginController {

// This will handle home page view of the page. 
    @GetMapping("/login")
    public String HomePageView() {
        return "admin/Login";
    }

    @GetMapping("/login-error")
    public String LoginErrorPage(Model model) {
        model.addAttribute("errorMessage", true);
        return "admin/Login";
    }
}
