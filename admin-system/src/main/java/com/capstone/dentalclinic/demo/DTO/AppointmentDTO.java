package com.capstone.dentalclinic.demo.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.capstone.dentalclinic.demo.model.Time;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.administrator.Employee;
import com.capstone.dentalclinic.demo.model.patient.Patient;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentDTO {

//    @NotNull(message = "Time is Required!")
//    @FutureOrPresent(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime dateAndTime;


    @NotNull(message = "Date is Required!")
    @FutureOrPresent(message = "Invalid Time Format")
//    @Past(message = "Invalid Date Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickDate;

    @NotNull(message = "Time is Required!")
    @Enumerated(EnumType.STRING)
//    @DateTimeFormat(pattern = "hh:mm a")
    private Time pickTime;

    private LocalTime endTime;
    
    @NotNull(message = "Services Required!")
    @Enumerated(EnumType.STRING)
    private Services services;

}
