package com.capstone.dentalclinic.demo.services.review;

import com.capstone.dentalclinic.demo.DTO.ReviewDTO;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.model.patient.Review;
import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import com.capstone.dentalclinic.demo.repository.patient.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final PatientRepository patientRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public void reviewPatient(String email,String message, String star) {

        final Patient patient =
                patientRepository.findPatientByEmailAddress(email.toLowerCase());
        System.out.println(" THE PATIENT  " + patient.getId());
        System.out.println(" THE PATIENT  " + patient.getFirstName());
        System.out.println("ENTERED");
        Review review = new Review();
        review.setPatient(patient);
        review.setStar(star);
        review.setMessage(message);

        reviewRepository.save(review);
    }
}
