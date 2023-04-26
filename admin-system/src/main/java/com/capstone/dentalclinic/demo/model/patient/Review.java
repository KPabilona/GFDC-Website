package com.capstone.dentalclinic.demo.model.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @SequenceGenerator( allocationSize = 1,
            name = "patient_review_sequence",
            sequenceName = "patient_review_sequence")
    @GeneratedValue(generator = "patient_review_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    private String message;

    @NotNull
    @NotBlank
    private String star;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
