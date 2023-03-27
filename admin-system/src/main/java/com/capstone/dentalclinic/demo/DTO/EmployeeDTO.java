package com.capstone.dentalclinic.demo.DTO;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotNull
    @Digits(message = "Number must contain 11 digits", fraction = 0, integer = 10)
    private String contactNumber;

    @NotNull
    @NotBlank(message = "First Name Required!")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last Name Required!")
    private String lastName;

    @NotNull
    @NotBlank(message = "Middle Name Required!")
    private String middleName;

    @NotNull
    @NotBlank(message = "Email Address Required!")
    @Email(message = "Invalid email do not include \" | \" and \" ' \" ", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\" +
            ".[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailAddress;

    @NotNull
    @Size(min = 8, message="Minimum of 8, Maximum of 30 digits")
    private String employeePassword;

    private String matchingPassword;

    @NotNull
    @NotBlank(message = "Address Required!")
    private String address;

    @NotNull(message = "Gender Required!")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Marital Status Required!")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @NotNull(message = "Birth Date Required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @Past(message = "Invalid Birth Date!")
    private LocalDate birthDate;

}