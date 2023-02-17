package com.capstone.dentalclinic.demo.services.administrator;

import com.capstone.dentalclinic.demo.model.admin.token.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    int setConfirmedAt(String token);

    LocalDateTime getConfirmedAt(String token);
}


