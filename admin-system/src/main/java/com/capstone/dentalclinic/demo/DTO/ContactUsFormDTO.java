package com.capstone.dentalclinic.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsFormDTO {

    @NotEmpty(message = "Name Required")
    private String fullName;

    @NotEmpty(message = "Email Address Required")
    @Email
    private String emailAddress;

    @NotNull(message = "Contact Number Required")
    @Digits(message = "Number must contain 11 digits", fraction = 0, integer = 10)
    private Long contactNumber;

    @NotEmpty(message = "Subject Required")
    private String subject;

    @NotEmpty(message = "Message Required")
    private String message;
}
