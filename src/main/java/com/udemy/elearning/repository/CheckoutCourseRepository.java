package com.udemy.elearning.repository;

import com.udemy.elearning.models.CheckoutCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutCourseRepository extends JpaRepository<CheckoutCourse, Long> {
    List<CheckoutCourse> findByCheckoutId(Long checkoutId);
}
