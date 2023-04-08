package com.capstone.dentalclinic.demo.services.patient;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.dentalclinic.demo.model.patient.token.PatientTokenConfirmation;
import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import com.capstone.dentalclinic.demo.repository.patient.PatientTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientTokenServiceImpl implements PatientTokenService{

    private final PatientTokenRepository patientTokenRepository;
    private final PatientRepository patientRepository;

    @Override
    public void saveConfirmationToken(PatientTokenConfirmation token) {
        patientTokenRepository.save(token);
    }

    @Override
    public Optional<PatientTokenConfirmation> getToken(String token) {
        return patientTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return patientTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public LocalDateTime getConfirmedAt(String token) {
        return patientTokenRepository.getConfirmedAt(token);
    }
    @Override
    @Transactional
    public String patientConfirmationToken(String token) {

        PatientTokenConfirmation patientTokenConfirmation =
                getToken(token).orElseThrow(() -> new IllegalStateException("Token Not Found"));

        if(patientTokenConfirmation.getConfirmedAt() != null) {
            return "token/AlreadyConfirmedToken";
        }

        LocalDateTime expiredAt = patientTokenConfirmation.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            return "token/ExpiredToken";
        }

        setConfirmedAt(token);

        patientRepository.enablePatientAccount(patientTokenConfirmation.getPatient().getEmailAddress());

        return "token/ConfirmedToken";
    }

}
