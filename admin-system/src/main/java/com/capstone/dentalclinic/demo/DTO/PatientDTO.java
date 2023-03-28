package com.capstone.dentalclinic.demo.DTO;

import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

//    information
//            - patient Given Name ✔ 🅱
//            - patient Middle Name ✔ 🅱
//            - patient Last Name ✔ 🅱
//            - patient Suffix ✔ 🅱
//            - patient Email Address ✔ 🆑
//            - patient Password ✔
//            - patient Complete Address ✔ 🆑
//            - patient Contact Number ✔ 🆑
//            - patient Date of Birth ✔ 🅱
//            - patient Gender ✔ 🅱
//            - patient Civil Status ✔ 🅱
//            - patient Physical Disability or Condition ✔ 🅱

    @NotNull
    @NotEmpty(message = "First Name Required!")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Middle Name Required!")
    private String middleName;

    @NotNull
    @NotEmpty(message = "Last Name Required!")
    private String lastName;

    private String suffix;

    @NotNull
    @NotBlank(message = "Email Address Required!")
    @Email(message = "Invalid email do not include \" | \" and \" ' \" ", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\" +
            ".[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailAddress;

    @NotNull
    @Size(min = 8, message="Minimum of 8 Characters Only")
    private String password;

    @NotEmpty(message = "Confirm Password Required!")
    private String confirmPassword;

    @NotNull
    @NotBlank(message = "Home Address Required!")
    private String homeAddress;

    @NotNull
    @Digits(message = "Number must contain 11 digits", fraction = 0, integer = 10)
    private long contactNumber;

    @NotNull(message = "Birth Date Required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Invalid Date!")
    private LocalDate birthDate;

    @NotNull(message = "Gender Required!")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Civil Status Required!")
    @Enumerated(EnumType.STRING)
    private MaritalStatus civilStatus;

    @NotNull
    @NotEmpty(message = "Indicate None if you don't have any.")
    private String physicalDisability;

}
