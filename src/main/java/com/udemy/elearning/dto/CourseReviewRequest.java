package com.udemy.elearning.dto;

import lombok.Data;

@Data
public class CourseReviewRequest {
    private String comment;
    private Long courseId;
    private Long userId;
    private Double rating;
}
