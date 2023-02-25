package com.capstone.dentalclinic.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsFormDTO {

    private String fullName;

    private String emailAddress;

    private Long contactNumber;

    private String subject;

    private String message;
}
