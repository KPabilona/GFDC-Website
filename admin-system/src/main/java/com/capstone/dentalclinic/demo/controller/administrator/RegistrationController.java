package com.capstone.dentalclinic.demo.controller.administrator;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import com.capstone.dentalclinic.demo.services.administrator.AdminService;
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

        final long contact = String.valueOf(employeeDTO.getContactNumber()).length();
        System.out.println("THE CONTACT NUMBER LENGTH " + contact);
        if( errors.hasErrors() ||
            errors.hasFieldErrors("emailAddress") ||
            contact != 10 ||
            adminService.emailAlreadyExist(employeeDTO.getEmailAddress())){

            System.out.println(" EMIAL VALIDATION" + errors.getFieldError("emailAddress"));
            if(adminService.emailAlreadyExist(employeeDTO.getEmailAddress())) {
                model.addAttribute("emailExist", "Email already Exist! ");
                model.addAttribute("genders", Gender.values());
                model.addAttribute("maritalStatus", MaritalStatus.values());
                return "admin/Registration";
            }else if (contact != 10) {
                model.addAttribute("genders", Gender.values());
                model.addAttribute("maritalStatus", MaritalStatus.values());
                model.addAttribute("contactNumberError", true);
                return "admin/Registration";
            }
            model.addAttribute("genders", Gender.values());
            model.addAttribute("maritalStatus", MaritalStatus.values());
            return "admin/Registration";
        }
        model.addAttribute("genders", Gender.values());
        model.addAttribute("maritalStatus", MaritalStatus.values());
        adminService.registerNewEmployee(employeeDTO);
        return "redirect:/admin/login-success";
    }
}