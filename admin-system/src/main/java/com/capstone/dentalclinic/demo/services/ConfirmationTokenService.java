package com.capstone.dentalclinic.demo.services;

import com.capstone.dentalclinic.demo.model.token.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    int setConfirmedAt(String token);
}


