package com.capstone.dentalclinic.demo.services.patient;

import com.capstone.dentalclinic.demo.model.administrator.token.ConfirmationToken;
import com.capstone.dentalclinic.demo.model.patient.token.PatientTokenConfirmation;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PatientTokenService {

    void saveConfirmationToken(PatientTokenConfirmation token);

    Optional<ConfirmationToken> getToken(String token);

    int setConfirmedAt(String token);

    LocalDateTime getConfirmedAt(String token);
}
