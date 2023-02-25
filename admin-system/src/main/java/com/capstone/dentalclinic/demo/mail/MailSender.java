package com.capstone.dentalclinic.demo.mail;

import org.springframework.scheduling.annotation.Async;

public interface MailSender {
    void sendConfirmationMail (String to, String email);

    void sendApproveRegistration(String to, String email);

    void contactUsForm(String subject, String emailAddress, String email);

}
