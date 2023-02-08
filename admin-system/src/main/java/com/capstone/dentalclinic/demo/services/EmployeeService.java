package com.capstone.dentalclinic.demo.services;

import java.util.List;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.exceptionhandler.EmailAlreadyTakenException;
import com.capstone.dentalclinic.demo.model.admin.Employee;

public interface EmployeeService {

    public List<Employee> getAllUser();

    public Employee getUserById(Long id);

    public Employee updateUser(Long id, Employee userModel);

    public void registerNewEmployee(EmployeeDTO employee);

    String confirmTokens(String token);

    boolean emailAlreadyExist(String email);
}
