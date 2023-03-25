package com.capstone.dentalclinic.demo.repository.administrator;

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

    Optional<Employee> findByEmailAddress(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee_tbl a " +
            "SET a.isEnable = TRUE WHERE a.emailAddress= ?1", nativeQuery = true)
    int enableAdminAccount(String email);

}
