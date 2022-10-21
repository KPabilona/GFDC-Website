package com.capstone.dentalclinic.demo.services;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.model.Employee;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllUser();

    public Employee getUserById(Long id);

    public Employee updateUser(Long id, Employee userModel);

    public void registerNewEmployee(EmployeeDTO employee);

    void confirmTokens(String token);
}
