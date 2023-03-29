package com.capstone.dentalclinic.demo.services.patient;

import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.mail.email_template.EmailTemplatePatient;
import com.capstone.dentalclinic.demo.model.Roles;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.model.patient.token.PatientTokenConfirmation;
import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import com.capstone.dentalclinic.demo.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServicesImpl implements UserDetailsService, PatientService{
    private final static String USER_NOT_FOUND_MSG =
            "User email %s not found";

    private final PatientRepository patientRepository;

    private final PatientTokenService patientTokenService;
    private final PasswordEncoder passwordEncoder;

    private MailSender mailSender;
    private final EmailTemplatePatient emailTemplatePatient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<Patient> findEmail = patientRepository.findByEmailAddress(email);
        final Patient patientEmail = patientRepository.EmailAddress(email);

        //&& patientEmail.isEnable()

        if(findEmail != null && findEmail.get().isEnable()) {
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


            System.out.println("PATIENT DTO PASSWORD: " + patientDTO.getPassword());
            System.out.println("PATIENT DTO CONFIRM PASSWORD: " + patientDTO.getConfirmPassword());

            final String encodedPassword = passwordEncoder.bcryptPasswordEncoder()
                    .encode(patientDTO.getPassword());

            Patient patient = new Patient();
            patient.setFirstName(patientDTO.getFirstName());
            patient.setMiddleName(patientDTO.getMiddleName());
            patient.setLastName(patientDTO.getLastName());
            patient.setSuffix(patientDTO.getSuffix());
            patient.setContactNumber(patientDTO.getContactNumber());
            patient.setEmailAddress(patientDTO.getEmailAddress());
            patient.setHomeAddress(patientDTO.getHomeAddress());
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
            final String token = UUID.randomUUID().toString();

            PatientTokenConfirmation tokenConfirmation =  new PatientTokenConfirmation(token,
                    LocalDateTime.now(), LocalDateTime.now().plusMinutes(60), patient);

            final String link = "http://localhost:8080/confirm?tokens=" + token;

            // this is where we email the patient for confirmation and to activate their account.
            mailSender.sendConfirmationMailPatient(patient.getEmailAddress(),
                    emailTemplatePatient.patientConfirmationRequest(patient.getFirstName(), link));

            patientTokenService.saveConfirmationToken(tokenConfirmation);

    }


    @Override
    public Patient findByEmailAddress(String emailAddress) {
        return patientRepository.findByPatientEmailAddress(emailAddress);
    }

    @Override
    public boolean isMatchedPassword(PatientDTO patientDTO) {
        return patientDTO.getConfirmPassword().equalsIgnoreCase(patientDTO.getPassword());
    }

    @Override
    public boolean forgotPassword(String email) {
        return patientRepository.enabledEmailAddress(email).isPresent();
    }

    @Override
    public String selectPatientAndToken(String emailAddress) {

        return patientRepository.selectPatientAndToken(emailAddress);
    }

    @Override
    public void setNewPasswordPatient(String password, String confirmPassword) {
    }

    public String patientTokenChecker(String token) {

        if(patientRepository.checkToken(token) == null || patientRepository.checkToken(token).isEmpty()) {
            return "token/ExpiredToken";
        }

        return "PatientWebPages/NewPassword";
    }
}
