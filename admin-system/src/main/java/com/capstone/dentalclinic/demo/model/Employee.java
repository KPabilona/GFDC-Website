package com.capstone.dentalclinic.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee_table")
public class Employee {
    
    @Id
    @SequenceGenerator( allocationSize = 1,
                        name = "user_table_sequence", 
                        sequenceName = "user_table_sequence")
    @GeneratedValue(generator = "user_table_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Digits(message = "Number must contain 11 digits", fraction = 0, integer = 10)
    private String contactNumber;

    // @NotBlank(message = "Age Required!")
    // private int age; 

    @NotBlank(message = "First Name Required!")
    private String firstName;

    @NotBlank(message = "Last Name Required!")
    private String lastName;

    @NotBlank(message = "Middle Name Required!")
    private String middleName;

    @NotBlank(message = "Email Address Required!")
    @Email
    private String emailAddress;

    @Size(min = 8, max = 30, message="Minimum of 8, Maximum of 30 digits")
    private String employeePassword;
    private String matchingPassword;

    @NotBlank(message = "Address Required!")
    private String address;

    @NotNull(message = "Gender Required!")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Birth Date Required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @Past(message = "Invalid Birth Date!")
    private Date birthDate;

    private boolean isEnable;
    
    public Employee(Long id,
            @NotBlank(message = "Contact Number Required!") @Size(min = 11, message = "Invalid Contact Number") String contactNumber,
            @NotBlank(message = "First Name Required!") String firstName,
            @NotBlank(message = "Last Name Required!") String lastName,
            @NotBlank(message = "Middle Name Required!") String middleName,
            @NotBlank(message = "Email Address Required!") @Email String emailAddress,
            @NotBlank(message = "Password Required!") @Size(min = 8, max = 30) String employeePassword,
            @NotBlank(message = "Address Required!") String address,
            @NotNull(message = "Gender Required!") Gender gender,
            @NotNull(message = "Birth Date Required!") Date birthDate) {
        this.id = id;
        this.contactNumber = contactNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.emailAddress = emailAddress;
        this.employeePassword = employeePassword;
        this.address = address;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Employee() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public String getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    // Setters
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmployeePassword(String password) {
        this.employeePassword = password;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date date) {
        this.birthDate = date;
    }
    
    @Override
    public String toString() {
        return "UserModel [contactNumber=" + contactNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", emailAddress=" + emailAddress + ", address=" + address + ", gender=" + gender + "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Employee other = (Employee) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (contactNumber == null) {
            if (other.contactNumber != null)
                return false;
        } else if (!contactNumber.equals(other.contactNumber))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (middleName == null) {
            if (other.middleName != null)
                return false;
        } else if (!middleName.equals(other.middleName))
            return false;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (gender != other.gender)
            return false;
        return true;
    }
}
