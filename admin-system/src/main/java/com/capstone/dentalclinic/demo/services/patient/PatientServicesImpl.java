package com.capstone.dentalclinic.demo.services.patient;

import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.model.Roles;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import com.capstone.dentalclinic.demo.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServicesImpl implements UserDetailsService, PatientService{
    private final static String USER_NOT_FOUND_MSG =
            "User email %s not found";

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Patient> email = patientRepository.findByEmailAddress(username);
        final Patient patientEmail = patientRepository.EmailAddress(username);

        if(email != null && patientEmail.isEnable()) {
            UserDetails userDetails = User.withUsername(patientEmail.getEmailAddress())
                    .password(patientEmail.getPassword())
                    .roles("PATIENT")
                    .authorities("PATIENT")
                    .build();

            return userDetails;
        }
        System.out.println("ERROR MESSAGE FROM PRINTLN = " + String.format(USER_NOT_FOUND_MSG, email));
        throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
    }

    @Override
    public boolean patientEmailAlreadyExist(String email) {
        return patientRepository.findByEmailAddress(email).isPresent();
    }

    @Override
    public void registerNewPatient(PatientDTO patientDTO) {

        if(patientDTO.getPassword().equalsIgnoreCase(patientDTO.getConfirmPassword())) {

            final String encodedPassword = passwordEncoder.bcryptPasswordEncoder()
                    .encode(patientDTO.getPassword());

            Patient patient = new Patient();
            patient.setFirstName(patientDTO.getFirstName());
            patient.setMiddleName(patientDTO.getMiddleName());
            patient.setLastName(patientDTO.getLastName());
            patient.setSuffix(patientDTO.getSuffix());
            patient.setContactNumber(patientDTO.getContactNumber());
            patient.setEmailAddress(patientDTO.getEmailAddress());
            patient.setPassword(encodedPassword);
            patient.setGender(patientDTO.getGender());
            patient.setBirthDate(patientDTO.getBirthDate());
            patient.setCivilStatus(patientDTO.getCivilStatus());
            patient.setPhysicalDisability(patientDTO.getPhysicalDisability());
            patient.setRoles(Roles.PATIENT);
            patient.setEnable(false);
            patient.setLocked(false);


            patientRepository.save(patient);

            // this is where we send tokens and emails for the newly registered patients
        }

    }


    @Override
    public boolean isMatchedPassword(PatientDTO patientDTO) {
        return patientDTO.getPassword().equalsIgnoreCase(patientDTO.getConfirmPassword());
    }
}
