package com.capstone.dentalclinic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.capstone.dentalclinic.demo.model.Employee;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    Optional<Employee> findByEmailAddress(String email);

    Employee findByEmailAddress(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Employee a " +
            "SET a.isEnable = TRUE WHERE a.emailAddress= ?1")
    int enableAppUser(String email);
}   
