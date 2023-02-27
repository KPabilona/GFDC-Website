package com.capstone.dentalclinic.demo.controller.patient;

import com.capstone.dentalclinic.demo.DTO.ContactUsFormDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.mail.email_template.EmailTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping
public class WebPages {

    private MailSender mailSender;
    private EmailTemplate emailTemplate;


    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("contactUs", new ContactUsFormDTO());
        return "PatientWebPages/index";
    }

    @PostMapping("/")
    public String ContactUsForm(@ModelAttribute("contactUs")
                                ContactUsFormDTO contactUsFormDTO,
                                BindingResult bindingResult) {
//        model.addAttribute("contactUs", new ContactUsFormDTO());
        if(bindingResult.hasErrors() == false) {
//            model.addAttribute("sub", true);
            mailSender.contactUsForm(contactUsFormDTO.getSubject(),
                    contactUsFormDTO.getEmailAddress(), emailTemplate.contactUstForm(contactUsFormDTO.getFullName(),
                            contactUsFormDTO.getContactNumber(), contactUsFormDTO.getMessage()));
        }
        return "redirect:/";
    }
}
