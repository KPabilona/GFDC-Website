package com.capstone.dentalclinic.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.model.EmployeeRole;
import com.capstone.dentalclinic.demo.model.token.ConfirmationToken;
import com.capstone.dentalclinic.demo.security.PasswordEncoder;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.model.Employee;
import com.capstone.dentalclinic.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements UserDetailsService,EmployeeService{
    private final static String USER_NOT_FOUND_MSG =
    "User email %s not found";

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employeeRepository.findByEmailAddress(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    // Get all The data in the UserModel database
    @Override
    public List<Employee> getAllUser() {
        return employeeRepository.findAll();
    }

    // get user by Id
    @Override
    public Employee getUserById(Long id) {
        Optional<Employee> userModelOptional = employeeRepository.findById(id);

        if(userModelOptional.isPresent()) {
            return userModelOptional.get();
        }
        return null;
    }

    // This will Update existing UserData
    @Override
    public Employee updateUser(Long id, Employee userModel) {
        Optional<Employee> userModelOptional = employeeRepository.findById(id);
        
        if(userModelOptional.isPresent()) {
            return employeeRepository.save(userModel);         
        }
        return null;
    }

    // This will Create data for the EmployeeModel
    public void registerNewEmployee(EmployeeDTO employeeDTO) {

        if(emailExist(employeeDTO.getEmailAddress()) ){
            throw new RuntimeException("Email already exist!");
        }

        String encode = passwordEncoder.bcryptPasswordEncoder().encode(employeeDTO.getEmployeePassword());

        Employee employee1 = new Employee();
        employee1.setFirstName(employeeDTO.getFirstName());
        employee1.setMiddleName(employeeDTO.getMiddleName());
        employee1.setLastName(employeeDTO.getLastName());
        employee1.setEmailAddress(employeeDTO.getEmailAddress());
        employee1.setEmployeePassword(encode);
        employee1.setAddress(employeeDTO.getAddress());
        employee1.setGender(employeeDTO.getGender());
        employee1.setContactNumber(employeeDTO.getContactNumber());
        employee1.setEmployeeRole(EmployeeRole.ADMIN);
        employee1.setBirthDate(employeeDTO.getBirthDate());
        employee1.setEnable(false);
        employee1.setLocked(false);
        employeeRepository.save(employee1);

        // Creating a Token before saving the employee
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), employee1);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        System.out.println("The Confirmation Token is " + confirmationToken.getToken());
    }

    @Override
    @Transactional
    public void confirmTokens(String token) {
        ConfirmationToken confirmationToken =
                confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

        if(confirmationToken.getConfirmedAt() != null ) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("email already confirmed");
        }

        confirmationTokenService.setConfirmedAt(token);

        enableEmployee(confirmationToken.getEmployee().getEmailAddress());
     }

    private int enableEmployee(String email) {
        return employeeRepository.enableAppUser(email);
    }

    private boolean emailExist(String email) {
        return employeeRepository.findByEmailAddress(email).isPresent();
    }
}
