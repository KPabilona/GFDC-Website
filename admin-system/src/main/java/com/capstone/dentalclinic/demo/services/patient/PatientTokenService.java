package com.capstone.dentalclinic.demo.services.patient;

import java.time.LocalDateTime;
import java.util.Optional;

import com.capstone.dentalclinic.demo.model.patient.token.PatientTokenConfirmation;

public interface PatientTokenService {

    void saveConfirmationToken(PatientTokenConfirmation token);

    Optional<PatientTokenConfirmation> getToken(String token);

    int setConfirmedAt(String token);

    LocalDateTime getConfirmedAt(String token);
    String patientConfirmationToken(String token);
}
