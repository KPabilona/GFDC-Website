package com.capstone.dentalclinic.demo.controller.patient;

import com.capstone.dentalclinic.demo.DTO.ContactUsFormDTO;
import com.capstone.dentalclinic.demo.DTO.ForgotPassword;
import com.capstone.dentalclinic.demo.DTO.PatientDTO;
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
    public String viewForgotPassword() {
        return "PatientWebPages/PatientForgotPassword";
    }

    @PostMapping("/forgot-password")
    public String sendForgotPasswordRequest(@ModelAttribute("patient") @Valid ForgotPassword forgotPassword,
                                            BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("checkEmail", patientService.forgotPassword(forgotPassword.getEmailAddress()));
            return "PatientWebPages/PatientForgotPassword";
        }
        return "PatientWebPages/PatientForgotPassword";
    }
}
