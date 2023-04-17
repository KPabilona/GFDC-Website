package com.capstone.dentalclinic.demo.model.appointment;

import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.Status;
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
@Entity
public class Appointment {

    @Id
    @SequenceGenerator( allocationSize = 1,
            name = "appointment_sequence_table",
            sequenceName = "appointment_sequence_table")
    @GeneratedValue(generator = "appointment_sequence_table",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:s")
    private LocalDateTime dateAndTime;

    @NotNull(message = "Time is Required!")
    @FutureOrPresent(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickDate;

    @NotNull(message = "Time is Required!")
    @DateTimeFormat(pattern = "hh:mm a")
    private String pickTime;

    @Column(nullable = true)
    private LocalTime endTime;
    
    @NotNull(message = "Services Required!")
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Services services;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    private Boolean isTaken;

    private String message;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
