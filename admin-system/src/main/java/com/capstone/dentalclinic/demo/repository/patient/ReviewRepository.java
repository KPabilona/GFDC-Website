package com.capstone.dentalclinic.demo.repository.patient;

import com.capstone.dentalclinic.demo.model.patient.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Transactional
    @Query("""
            SELECT r FROM Review r
            """)
    List<Review> findAll();

}
