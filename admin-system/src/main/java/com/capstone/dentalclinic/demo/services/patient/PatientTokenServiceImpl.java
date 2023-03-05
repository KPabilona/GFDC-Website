package com.capstone.dentalclinic.demo.services.patient;

import com.capstone.dentalclinic.demo.model.administrator.token.ConfirmationToken;
import com.capstone.dentalclinic.demo.model.patient.token.PatientTokenConfirmation;
import com.capstone.dentalclinic.demo.repository.patient.PatientTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientTokenServiceImpl implements PatientTokenService{

    private PatientTokenRepository patientTokenRepository;

    @Override
    public void saveConfirmationToken(PatientTokenConfirmation token) {
        patientTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
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

}
