package com.udemy.elearning.repository;


import com.udemy.elearning.models.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long> {
    List<CourseLesson> findByCourseId(Long courseId);

    @Query("SELECT c FROM CourseLesson c WHERE c.courseId = :courseId ORDER BY c.id DESC")
    List<CourseLesson> findByCourseIdSorted(@Param("courseId") Long courseId);
}
