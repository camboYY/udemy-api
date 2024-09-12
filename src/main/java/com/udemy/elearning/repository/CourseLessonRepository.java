package com.udemy.elearning.repository;


import com.udemy.elearning.models.CourseLesson;
import com.udemy.elearning.models.CourseTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long> {
    List<CourseLesson> findByCourseId(Long courseId);
}
