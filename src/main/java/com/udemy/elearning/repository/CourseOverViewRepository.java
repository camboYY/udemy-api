package com.udemy.elearning.repository;


import com.udemy.elearning.models.CourseOverView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOverViewRepository extends JpaRepository<CourseOverView, Long> {
    List<CourseOverView> findByCourseId(Long courseId);
}
