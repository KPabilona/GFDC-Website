package com.capstone.dentalclinic.demo.services.review;

import com.capstone.dentalclinic.demo.DTO.ReviewDTO;
import com.capstone.dentalclinic.demo.model.patient.Patient;

public interface ReviewService {
    void reviewPatient(String email, String message, String star);
}
