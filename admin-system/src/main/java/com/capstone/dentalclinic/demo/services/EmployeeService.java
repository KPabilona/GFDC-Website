package com.capstone.dentalclinic.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.model.Employee;
import com.capstone.dentalclinic.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Gett all The data in the UserModel database 
    public List<Employee> getAllUser() {
        return employeeRepository.findAll();
    }

    // get user by Id 
    public Employee getUserById(Long id) {
        Optional<Employee> userModelOptional = employeeRepository.findById(id);
        
        if(userModelOptional.isPresent()) {
            return userModelOptional.get();
        }
        return null;
    }

    // This will Create data for the UserModel
    // public Employee createUser(Employee userModel) {
    //     return employeeRepository.save(userModel);
    // }

    // This will Update existing UserData 
    public Employee updateUser(Long id, Employee userModel) {
        Optional<Employee> userModelOptional = employeeRepository.findById(id);
        
        if(userModelOptional.isPresent()) {
            return employeeRepository.save(userModel);         
        }
        return null;
    }

    // This will Create data for the UserModel
    public Employee registerNewEmployee(Employee employee) {
        if(emailExist(employee.getEmailAddress())) {
            throw new RuntimeException("The Email AlreadyExist!");
        }

        Employee emp = new Employee();
        emp.setFirstName(employee.getFirstName());
        emp.setMiddleName(employee.getMiddleName());
        emp.setLastName(employee.getLastName());
        emp.setContactNumber(employee.getContactNumber());
        emp.setEmailAddress(employee.getEmailAddress());
        emp.setEmployeePassword(employee.getEmployeePassword());
        emp.setAddress(employee.getAddress());
        emp.setGender(employee.getGender());
        emp.setBirthDate(employee.getBirthDate());

        return employeeRepository.save(emp);
    }

    private boolean emailExist(String email) {
        return employeeRepository.findByEmailAddress(email) != null;
    }
}
