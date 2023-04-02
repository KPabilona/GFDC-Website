package com.capstone.dentalclinic.demo.repository.patient;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.dentalclinic.demo.model.patient.token.PatientTokenConfirmation;

@Repository
public interface PatientTokenRepository extends JpaRepository<PatientTokenConfirmation, Long> {

    Optional<PatientTokenConfirmation> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE PatientTokenConfirmation c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);

    @Transactional
    @Query("SELECT c.confirmedAt FROM ConfirmationToken c WHERE c.token = ?1")
    LocalDateTime getConfirmedAt(String token);

}
