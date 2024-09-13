package com.udemy.elearning.dto;

import lombok.Data;

@Data
public class CourseOverViewRequest {
    private String comment;
    private Long courseId;
    private Long userId;
    private Double rating;
}
