package com.capstone.dentalclinic.demo.controller;

import javax.validation.Valid;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.capstone.dentalclinic.demo.model.Employee;
import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.services.EmployeeServiceImpl;

@Controller
@AllArgsConstructor
@RequestMapping("/system/admin")
public class RegistrationController {
    
    private final EmployeeService employeeService;

    // This will handle the registration page view of the page.
    @GetMapping("/registration")
    public String RegistrationPageView(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("genders", Gender.values());
        return "admin/Registration";
    }

    @PostMapping("/registration")
    public String RegistrationSubmittion(@ModelAttribute("employee") @Valid EmployeeDTO employee,
                                         BindingResult errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("genders", Gender.values());
            return "admin/Registration";
        }
        employeeService.registerNewEmployee(employee);
        return "redirect:/system/admin/login";
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) {
        return employeeService.confirmTokens(token);
    }
}