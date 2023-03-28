package com.capstone.dentalclinic.demo.DTO;


import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDTO {

    @NotBlank(message = "Must not be blank")
    @Email(message = "Invalid email do not include \" | \" and \" ' \" ", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\" +
            ".[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailAddress;

    @NotNull
    @Size(min = 8, message="Minimum of 8 Characters")
    private String newPassword;

    @NotNull
    @NotBlank
    private String confirmNewPassword;
}
