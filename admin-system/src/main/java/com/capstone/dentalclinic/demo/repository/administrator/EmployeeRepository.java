package com.capstone.dentalclinic.demo.repository.administrator;

import com.capstone.dentalclinic.demo.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.dentalclinic.demo.model.administrator.Employee;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee EmailAddress(String email);

    @Transactional
    @Query("""
            SELECT e FROM Employee e WHERE e.emailAddress = ?1
            """)
    Employee findEmployeeByEmailAddress(String emailAddress);

    Optional<Employee> findByEmailAddress(String email);

    @Transactional
    @Modifying
    @Query("""
            UPDATE Employee a  
            SET a.isEnable = TRUE WHERE a.emailAddress= ?1
            """)
    int enableAdminAccount(String email);

}
