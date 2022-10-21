package com.capstone.dentalclinic.demo.services;

import java.util.List;
import java.util.Optional;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.model.EmployeeRole;
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

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements UserDetailsService,EmployeeService{
    private final static String USER_NOT_FOUND_MSG =
    "User email %s not found";

    private final EmployeeRepository    employeeRepository;
    private final PasswordEncoder passwordEncoder;
    
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
    public Employee registerNewEmployee(EmployeeDTO employeeDTO) {

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

        return employeeRepository.save(employee1);

    }

    private boolean emailExist(String email) {
        return employeeRepository.findByEmailAddress(email).isPresent();
    }
}
