package com.capstone.dentalclinic.demo.model.patient;

import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import com.capstone.dentalclinic.demo.model.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Patient implements UserDetails {

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

    @Id
    @SequenceGenerator( allocationSize = 1,
            name = "patient_sequence_table",
            sequenceName = "patient_sequence_table")
    @GeneratedValue(generator = "patient_sequence_table",
            strategy = GenerationType.SEQUENCE)
    private long id;

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
    @NotEmpty(message = "Email Address Required!")
    private String emailAddress;

    @NotNull
    @NotBlank
    @Size(min = 8, message="Minimum of 8, Maximum of 30 digits")
    private String password;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MaritalStatus civilStatus;

    @NotNull
    @NotEmpty(message = "Indicate None if you don't have any.")
    private String physicalDisability;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Roles roles;

    private boolean isEnable = false;
    private boolean isLocked = false;

    public Patient(String firstName,
                   String middleName,
                   String lastName,
                   String suffix,
                   String emailAddress,
                   String password,
                   String homeAddress,
                   long contactNumber,
                   LocalDate birthDate,
                   Gender gender,
                   MaritalStatus civilStatus,
                   String physicalDisability,
                   Roles roles,
                   boolean isEnable,
                   boolean isLocked) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.emailAddress = emailAddress;
        this.password = password;
        this.homeAddress = homeAddress;
        this.contactNumber = contactNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.civilStatus = civilStatus;
        this.physicalDisability = physicalDisability;
        this.roles = roles;
        this.isEnable = isEnable;
        this.isLocked = isLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roles.getDisplayRole());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
