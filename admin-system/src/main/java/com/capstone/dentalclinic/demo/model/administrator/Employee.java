package com.capstone.dentalclinic.demo.model.administrator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import com.capstone.dentalclinic.demo.model.Roles;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
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
//    @Column(name = "contact_number")
    private String contactNumber;


    @NotNull
    @NotBlank(message = "First Name Required!")
//    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last Name Required!")
//    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotBlank(message = "Middle Name Required!")
//    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @NotBlank(message = "Email Address Required!")
    @Email(message = "Invalid email do not include \" | \" and \" ' \" ", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\" +
            ".[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailAddress;

    @NotNull
    @NotBlank
    @Size(min = 8, message="Minimum of 8, Maximum of 30 digits")
//    @Column(name = "password")
    private String employeePassword;

    @NotNull
    @NotBlank(message = "Address Required!")
//    @Column(name = "address")
    private String address;

    @NotNull(message = "Gender Required!")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotNull(message = "Birth Date Required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @Past(message = "Invalid Birth Date!")
//    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull(message = "Marital Status Required!")
    @Enumerated(EnumType.STRING)
//    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles roles;

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
                    Roles roles,
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
