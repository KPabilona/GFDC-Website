package com.capstone.dentalclinic.demo.model.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @NotNull(message = "Time is Required!")
    @FutureOrPresent(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAndTime;


    @NotNull(message = "Time is Required!")
    @FutureOrPresent(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickDate;

    @NotNull(message = "Time is Required!")
    @Enumerated(EnumType.STRING)
    @DateTimeFormat(pattern = "hh:mm a")
    private Time pickTime;

    private LocalTime endTime;
    
    @NotNull(message = "Gender Required!")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Services services;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "employee_id", nullable = false)
//    private Employee employee;

}
