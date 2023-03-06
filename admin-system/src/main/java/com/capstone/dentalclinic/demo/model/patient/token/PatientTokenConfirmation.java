package com.capstone.dentalclinic.demo.model.patient.token;

import com.capstone.dentalclinic.demo.model.patient.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PatientTokenConfirmation {

    @Id
    @SequenceGenerator( allocationSize = 1,
            name = "patient_confirmation_token_sequence",
            sequenceName = "patient_confirmation_token_sequence")
    @GeneratedValue(generator = "patient_confirmation_token_sequence",
            strategy = GenerationType.SEQUENCE)
    Long Id;

    @NotNull
    @Column(nullable = false)
    private String token;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "employee_id",
            nullable = false)
    private Patient patient;

    public PatientTokenConfirmation(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             Patient patient) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.patient = patient;
    }

}
