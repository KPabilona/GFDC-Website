package com.capstone.dentalclinic.demo.controller.administrator;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import com.capstone.dentalclinic.demo.services.administrator.AdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class RegistrationController {
    
    private final AdminService adminService;

    // This will handle the registration page view of the page.
    @GetMapping("/registration")
    public String RegistrationPageView(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("maritalStatus", MaritalStatus.values());
        return "admin/Registration";
    }

    // This will handle the registration page Submittion process.
    @PostMapping("/registration")
    public String RegistrationSubmittion(@ModelAttribute("employee") @Valid EmployeeDTO employeeDTO,
                                         BindingResult errors, Model model){
        if(errors.hasErrors()){
            if(adminService.emailAlreadyExist(employeeDTO.getEmailAddress())) {
                model.addAttribute("emailExist", "Email already Exist! ");
                return "admin/Registration";
            }
            model.addAttribute("genders", Gender.values());
            model.addAttribute("maritalStatus", MaritalStatus.values());
            return "admin/Registration";
        }
 
        adminService.registerNewEmployee(employeeDTO);
        return "redirect:/admin/login";
    }
}