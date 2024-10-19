package com.udemy.elearning.mapper;
import com.udemy.elearning.models.UserCompleteLesson;
import lombok.Data;

@Data
public class UserCompleteLessonResponse {
    private Long lessonId;
    private Long userId;
    private long id;

    public UserCompleteLessonResponse(UserCompleteLesson userCompleteLesson) {
        this.setLessonId(userCompleteLesson.getLessonId());
        this.setUserId(userCompleteLesson.getUserId());
        this.setId(userCompleteLesson.getId());
    }
}
