package com.udemy.elearning.repository;

import com.udemy.elearning.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategoryId(Long categoryId);

    @Query("SELECT c FROM Course c LEFT JOIN CourseTags ct ON c.id = ct.courseId WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(ct.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Course> searchByString(String keyword, PageRequest pageRequest);

}
