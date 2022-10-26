package com.capstone.dentalclinic.demo.controller;

import javax.validation.Valid;

import com.capstone.dentalclinic.demo.repository.EmployeeRepository;
import com.capstone.dentalclinic.demo.services.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.dentalclinic.demo.model.Employee;

@Controller
@RequestMapping("/system/admin")
@AllArgsConstructor
public class LoginController {

    private final EmployeeServiceImpl employeeServiceImpl;
    private final EmployeeRepository employeeRepository;

    // This will handle home page view of the page.
    @GetMapping("/login")
    public String HomePageView() {
        return "admin/Login";
    }
}
