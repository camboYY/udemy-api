package com.udemy.elearning.repository;

import com.udemy.elearning.models.CheckoutCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutCourseRepository extends JpaRepository<CheckoutCourse, Long> {
}
