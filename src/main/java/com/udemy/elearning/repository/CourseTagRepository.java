package com.udemy.elearning.repository;


import com.udemy.elearning.models.CourseTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTags, Long> {
    List<CourseTags> findByCourseId(Long courseId);
}
