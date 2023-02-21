package com.capstone.dentalclinic.demo.services.patient;

import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientServicesImpl implements UserDetailsService, PatientService{

    private final PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public boolean patientEmailAlreadyExist(String email) {
        return patientRepository.findByEmailAddress(email).isPresent();
    }
}
