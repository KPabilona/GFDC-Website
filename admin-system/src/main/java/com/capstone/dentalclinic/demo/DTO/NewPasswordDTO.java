package com.capstone.dentalclinic.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDTO {

    @NotNull
    @Size(min = 8, message="Minimum of 8 Characters")
    private String newPassword;

    @NotNull
    @NotBlank
    private String confirmNewPassword;
}

