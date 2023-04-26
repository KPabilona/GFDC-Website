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
    public void reviewPatient(ReviewDTO reviewDTO) {

        final Patient patient =
                patientRepository.findPatientByEmailAddress(reviewDTO.getEmailAddress());

        System.out.println("ENTERED");
        Review review = new Review();
        review.setStar(reviewDTO.getStar());
        review.setMessage(reviewDTO.getMessage());
        review.setPatient(patient);

        reviewRepository.save(review);
    }
}
