package com.capstone.dentalclinic.demo.services.administrator;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.model.administrator.token.ConfirmationToken;
import com.capstone.dentalclinic.demo.repository.administrator.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public LocalDateTime getConfirmedAt(String token) {
        return confirmationTokenRepository.getConfirmedAt(token);
    }
}
