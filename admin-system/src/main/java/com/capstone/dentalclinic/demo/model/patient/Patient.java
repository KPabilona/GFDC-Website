package com.capstone.dentalclinic.demo.model.patient;

import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import com.capstone.dentalclinic.demo.model.Roles;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
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
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
//@Table(name = "patient_tbl")
public class Patient implements UserDetails {

    @Id
    @SequenceGenerator( allocationSize = 1,
            name = "patient_sequence_table",
            sequenceName = "patient_sequence_table")
    @GeneratedValue(generator = "patient_sequence_table",
            strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    @NotEmpty(message = "First Name Required!")
//    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Middle Name Required!")
//    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @NotEmpty(message = "Last Name Required!")
//    @Column(name = "last_name")
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
    @NotBlank
    @Size(min = 8, message="Minimum of 8 characters")
//    @Column(name = "password")
    private String patientPassword;

    @NotNull
    @NotBlank(message = "Home Address Required!")
//    @Column(name = "address")
    private String homeAddress;

    @NotNull
    @Digits(message = "Number must contain 11 digits", fraction = 0, integer = 10)
//    @Column(name = "contact_number")
    private long contactNumber;

    @NotNull(message = "Birth Date Required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Invalid Date!")
//    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
//    @Column(name = "gender")
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
//    @Column(name = "civil_status")
    private MaritalStatus civilStatus;

    @NotNull
    @NotEmpty(message = "Indicate None if you don't have any.")
//    @Column(name = "physical_disability")
    private String physicalDisability;

    @NotNull
    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
    private Roles roles;

    @OneToMany(mappedBy = "patient",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    private boolean isEnable = false;
    private boolean isLocked = false;

    public Patient(String firstName,
                   String middleName,
                   String lastName,
                   String suffix,
                   String emailAddress,
                   String patientPassword,
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
        this.patientPassword = patientPassword;
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
        return emailAddress;
    }

    @Override
    public String getPassword() {
        return patientPassword;
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
