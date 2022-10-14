package com.capstone.dentalclinic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.dentalclinic.demo.model.Employee;

@Repository
public interface UserModelRepository extends JpaRepository<Employee,Long> {
    
}
