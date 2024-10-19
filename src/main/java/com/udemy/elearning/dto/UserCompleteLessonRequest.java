package com.udemy.elearning.dto;

import lombok.Data;

@Data
public class UserCompleteLessonRequest {
    private Long userId;
    private Long lessonId;
}
