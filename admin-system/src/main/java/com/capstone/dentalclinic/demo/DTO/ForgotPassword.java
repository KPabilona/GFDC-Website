package com.capstone.dentalclinic.demo.DTO;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ForgotPassword {

    @NotNull
    @NotBlank
    @Email
    private String emailAddress;

    @NotNull
    @Size(min = 8, message="Minimum of 8 Characters")
    private String newPassword;

    private String confirmNewPassword;
}
