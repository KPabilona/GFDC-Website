package com.capstone.dentalclinic.demo.mail;

import org.springframework.scheduling.annotation.Async;

public interface MailSender {
    // Confirmation from the Administrator part.
    void sendConfirmationMail (String to, String email);

    void sendApproveRegistration(String to, String email);

    // Confirmation from the Patient Part.
    void sendConfirmationMailPatient (String to, String email);


    // Contact Us Form in Landing page.
    void contactUsForm(String subject, String emailAddress, String email);

}
