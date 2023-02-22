package com.capstone.dentalclinic.demo.services.administrator;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.model.administrator.token.ConfirmationToken;
import com.capstone.dentalclinic.demo.repository.administrator.AdminTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminTokenServiceImpl implements AdminTokenService {

    private final AdminTokenRepository adminTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        adminTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return adminTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return adminTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public LocalDateTime getConfirmedAt(String token) {
        return adminTokenRepository.getConfirmedAt(token);
    }
}
