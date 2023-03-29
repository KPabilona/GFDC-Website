package com.capstone.dentalclinic.demo.controller.patient;

import com.capstone.dentalclinic.demo.DTO.ContactUsFormDTO;
import com.capstone.dentalclinic.demo.DTO.ForgotPasswordDTO;
import com.capstone.dentalclinic.demo.DTO.NewPasswordDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.mail.email_template.EmailTemplate;
import com.capstone.dentalclinic.demo.mail.email_template.EmailTemplateForgotPassword;
import com.capstone.dentalclinic.demo.services.patient.PatientService;
import com.capstone.dentalclinic.demo.services.patient.PatientServicesImpl;
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
    private final EmailTemplateForgotPassword emailTemplateForgotPassword;

    private final PatientService patientService;

    private final PatientServicesImpl impl;


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
        System.out.println(bindingResult.getAllErrors());
        if(!patientService.forgotPassword(forgotPasswordDto.getEmailAddress())) {
            model.addAttribute("checkEmail", true);
            return "PatientWebPages/PatientForgotPassword";
        }else if(bindingResult.hasErrors()) {
            System.out.println("BINDING ERROR");
            return "PatientWebPages/PatientForgotPassword";
        }

        final String token = patientService.selectPatientAndToken(forgotPasswordDto.getEmailAddress().toString());
        final String link = "http://localhost:8080/new-password?token=" + token;

        mailSender.resetPassword(forgotPasswordDto.getEmailAddress(),
                emailTemplateForgotPassword.forgotPasswordRequest(forgotPasswordDto.getEmailAddress(),link));

        return "PatientWebPages/PatientForgotPassword";
     }

    @GetMapping("/new-password")
    public String viewNewPassword(@RequestParam("token") String token,
                                  @ModelAttribute("newPassword") NewPasswordDTO newPasswordDTO,
                                  Model model) {
        model.addAttribute("newPassword", new NewPasswordDTO());
        return patientService.patientTokenChecker(token);
    }
    @PostMapping("/new-password")
    public String setNewPassword(@ModelAttribute("newPassword") NewPasswordDTO newPasswordDTO,
                               Model model) {
        model.addAttribute("newPassword", new NewPasswordDTO());
        patientService.setPatientNewPassword(newPasswordDTO.getNewPassword());
        return "PatientWebPages/NewPassword";
    }
} 
