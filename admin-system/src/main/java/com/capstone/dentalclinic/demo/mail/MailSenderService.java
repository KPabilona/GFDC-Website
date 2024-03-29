package com.capstone.dentalclinic.demo.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailSenderService implements MailSender{

    private final JavaMailSender javaMailSender;

    private final String adminEmail = "gillegoflores.dentalclinic@gmail.com";

    // This is for the Administrator part
    @Override
    @Async
    public void sendConfirmationMail(String from, String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setTo(adminEmail); // business Email
            helper.setFrom(from); // email of an employee/personel
            helper.setSubject("NEW RECEPTIONIST ACCOUNT");
            helper.setText(email, true);
            javaMailSender.send(message);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void sendApproveRegistration(String to, String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setTo(to);
            helper.setFrom(adminEmail);
            helper.setSubject("REQUEST APPROVED");
            helper.setText(email, true);
            javaMailSender.send(message);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    // This is for the Patient Part.
    @Override
    @Async
    public void sendConfirmationMailPatient(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(adminEmail);
            mimeMessageHelper.setSubject("ACCOUNT VERIFICATION");
            mimeMessageHelper.setText(email, true);
            javaMailSender.send(mimeMessage);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // This is for the Contact Us Form in the Landing Page.
    @Override
    @Async
    public void contactUsForm(String subject, String emailAddress, String email) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom(emailAddress);
            helper.setTo(adminEmail);
            helper.setSubject(subject.toString());
            helper.setText(email, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Async
    public void resetPassword(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            
            helper.setTo(to);
            helper.setFrom(adminEmail);
            helper.setSubject("PASSWORD RESET REQUEST");
            helper.setText(email, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e.getMessage());
        }
    }

    // Cancellation of Appointment
    @Override
    public void cancelAppointment(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");

            helper.setTo(to);
            helper.setFrom(adminEmail);
            helper.setSubject("CANCELLATION OF APPOINTMENT");
            helper.setText(email, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void appointmentNotification(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");

            helper.setTo(to);
            helper.setFrom(adminEmail);
            helper.setSubject("APPOINTMENT CONFIRMED!");
            helper.setText(email, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e.getMessage());
        }
    }
}
