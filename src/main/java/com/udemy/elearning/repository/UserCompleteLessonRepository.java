package com.udemy.elearning.repository;


import com.udemy.elearning.models.UserCompleteLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCompleteLessonRepository extends JpaRepository<UserCompleteLesson, Long> {
    void deleteByUserIdAndLessonId(Long userId, Long lessonId);
}
