package com.capstone.dentalclinic.demo.model.patient.appointment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


}
