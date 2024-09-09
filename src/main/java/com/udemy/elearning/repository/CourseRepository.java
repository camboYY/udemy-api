package com.udemy.elearning.repository;

import com.udemy.elearning.models.Category;
import com.udemy.elearning.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
