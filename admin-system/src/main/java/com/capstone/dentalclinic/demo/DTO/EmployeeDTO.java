package com.capstone.dentalclinic.demo.DTO;

import com.capstone.dentalclinic.demo.model.EmployeeRole;
import com.capstone.dentalclinic.demo.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

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

    @NotNull(message = "Birth Date Required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @Past(message = "Invalid Birth Date!")
    private LocalDate birthDate;

}