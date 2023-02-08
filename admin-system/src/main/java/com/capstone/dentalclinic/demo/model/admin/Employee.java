package com.capstone.dentalclinic.demo.model.admin;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Employee implements UserDetails {
    
    @Id
    @SequenceGenerator( allocationSize = 1,
                        name = "employee_sequence_table",
                        sequenceName = "employee_sequence_table")
    @GeneratedValue(generator = "employee_sequence_table", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Digits(message = "Number must contain 11 digits", fraction = 0, integer = 10)
    private String contactNumber;

    // @NotBlank(message = "Age Required!")
    // private int age;

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
    @NotBlank
    @Size(min = 8, message="Minimum of 8, Maximum of 30 digits")
    private String employeePassword;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @NotNull(message = "Marital Status Required!")
    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;

    private boolean isEnable = false;

    private boolean isLocked = false;

    public Employee(String contactNumber,
                    String firstName,
                    String lastName,
                    String middleName,
                    String emailAddress,
                    String employeePassword,
                    String address,
                    Gender gender,
                    LocalDate birthDate,
                    EmployeeRole employeeRole,
                    boolean isEnable,
                    boolean isLocked) {
        this.contactNumber = contactNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.emailAddress = emailAddress;
        this.employeePassword = employeePassword;
        this.address = address;
        this.gender = gender;
        this.birthDate = birthDate;
        this.employeeRole = employeeRole;
        this.isEnable = isEnable;
        this.isLocked = isLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(EmployeeRole.ADMIN.getDisplayRole());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return employeePassword;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
