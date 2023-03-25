package com.capstone.dentalclinic.demo.repository.appointment;

import com.capstone.dentalclinic.demo.model.patient.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}

