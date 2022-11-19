package com.capstone.dentalclinic.demo.mail;

public interface MailSender {
    void sendConfirmationMail (String to, String email);

    void sendApproveRegistration(String to, String email);
}
