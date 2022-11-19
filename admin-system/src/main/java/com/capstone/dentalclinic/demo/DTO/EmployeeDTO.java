package com.capstone.dentalclinic.demo.DTO;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

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
    @Email
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