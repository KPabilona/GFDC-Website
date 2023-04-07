package com.capstone.dentalclinic.demo.model.appointment;

import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.Time;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Appointment {

    @Id
    @SequenceGenerator( allocationSize = 1,
            name = "appointment_sequence_table",
            sequenceName = "appointment_sequence_table")
    @GeneratedValue(generator = "appointment_sequence_table",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    
//    @NotNull(message = "Time is Required!")
//    @FutureOrPresent(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime dateAndTime;


    @NotNull(message = "Time is Required!")
    @FutureOrPresent(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private LocalDate pickDate;

    @NotNull(message = "Time is Required!")
//    @Enumerated(EnumType.STRING)
    @DateTimeFormat(pattern = "hh:mm a")
    private String pickTime;

    @Column(nullable = true)
    private LocalTime endTime;
    
    @NotNull(message = "Services Required!")
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Services services;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "employee_id", nullable = false)
//    private Employee employee;

}
