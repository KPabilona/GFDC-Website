package com.capstone.dentalclinic.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.dentalclinic.demo.model.Employee;
import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.services.EmployeeService;

@Controller
@RequestMapping("/system")
public class RegistrationController {
    
    private final EmployeeService employeeService;
    @Autowired
    public RegistrationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // This will handle the registration page view of the page.
    @GetMapping("/registration")
    public String RegistrationPageView(Employee employee, Model model) {
        model.addAttribute(employee);
        model.addAttribute("employee", new Employee());
        model.addAttribute("genders", Gender.values());
        return "admin/Registration";
    }

    @PostMapping("/registration")
    public String RegistrationSubmition(@ModelAttribute @Valid Employee employee, BindingResult errors, Model model) { 
        
        if(errors.hasErrors()){
            model.addAttribute("genders", Gender.values());
            return "admin/Registration";
        }else {
            employeeService.registerNewEmployee(employee);
            return "admin/Login";
        }
    }
}
