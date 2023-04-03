package com.capstone.dentalclinic.demo.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.administrator.Employee;
import com.capstone.dentalclinic.demo.model.patient.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    @NotNull(message = "Time is Required!")
    @Past(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy/M/d")
    private LocalDateTime dateAndTime;


    @NotNull(message = "Time is Required!")
    @Past(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy/M/d ")
    private LocalDate pickDate;

    @NotNull(message = "Time is Required!")
    @Past(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "h:mm tt")
    private LocalTime pickTime;

    private LocalTime endTime;
    
    @NotNull(message = "Gender Required!")
    @Enumerated(EnumType.STRING)
    @Column(name = "service")
    private Services services;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
