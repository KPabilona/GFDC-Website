package com.capstone.dentalclinic.demo.DTO;

import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.Time;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class AppointmentDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime dateAndTime;


    @NotNull(message = "Date is Required!")
    @FutureOrPresent(message = "Invalid Date Format, It should be Present or Future! ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickDate;

    @NotNull(message = "Time is Required!")
    @Enumerated(EnumType.STRING)

    private Time pickTime;

    @Column(nullable = true)
    private Boolean isTaken;

    private LocalTime endTime;

    private Long patientId;
    
    @NotNull(message = "Services Required!")
    @Enumerated(EnumType.STRING)
    private Services services;

}
