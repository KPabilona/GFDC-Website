package com.capstone.dentalclinic.demo.controller.patient;

import com.capstone.dentalclinic.demo.DTO.ContactUsFormDTO;
import com.capstone.dentalclinic.demo.DTO.ForgotPasswordDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.mail.email_template.EmailTemplate;
import com.capstone.dentalclinic.demo.services.patient.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping
public class WebPages {

    private final MailSender mailSender;
    private final EmailTemplate emailTemplate;

    private final PatientService patientService;


    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("contactUs", new ContactUsFormDTO());
        return "PatientWebPages/index";
    }

    @PostMapping("/")
    public String ContactUsForm(@ModelAttribute("contactUs") @Valid
                                ContactUsFormDTO contactUsFormDTO,
                                BindingResult bindingResult,
                                Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("invalidEmail", true);
            return "PatientWebPages/index";
        }
        mailSender.contactUsForm(contactUsFormDTO.getSubject(),
                contactUsFormDTO.getEmailAddress(), emailTemplate.contactUstForm(contactUsFormDTO.getFullName(),
                        contactUsFormDTO.getContactNumber(), contactUsFormDTO.getMessage()));
                model.addAttribute("successMessage", true);
        return "PatientWebPages/index";
    }

    @GetMapping("/AboutUs")
    public String aboutUsPage() {
        return "PatientWebPages/PatientAboutUsPage";
    }

    @GetMapping("/Service")
    public String servicesPage() {
        return "PatientWebPages/PatientServicesPage";
    }

    // forgot password
    @GetMapping("/forgot-password") 
    public String viewForgotPassword(Model model) {
        model.addAttribute("forgotPassword", new ForgotPasswordDTO());
        return "PatientWebPages/PatientForgotPassword";
    }

    @PostMapping("/forgot-password")
    public String sendForgotPasswordRequest(@ModelAttribute("forgotPassword") @Valid ForgotPasswordDTO forgotPasswordDto,
                                            BindingResult bindingResult, Model model) {

        if(!patientService.forgotPassword(forgotPasswordDto.getEmailAddress())) {
            model.addAttribute("checkEmail", true);
            return "PatientWebPages/PatientForgotPassword";
        }else if(bindingResult.hasErrors()) {
            return "PatientWebPages/PatientForgotPassword";
        }
        return "PatientWebPages/PatientForgotPassword";
    }

    @GetMapping("/new-password")
    public String viewNewPassword(Model model) {
        model.addAttribute("forgotPassword", new ForgotPasswordDTO());
        return "PatientWebPages/NewPassword";
    }

    @GetMapping("/new-password")
    public String setNewPassword(@ModelAttribute("newPassword") @Valid ForgotPasswordDTO forgotPasswordDTO,
                                BindingResult bindingResult, Model model) {
        return "";
    }
} 
