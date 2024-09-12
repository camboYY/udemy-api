package com.udemy.elearning.dto;

import lombok.Data;

@Data
public class CourseLessonRequest {
    private String title;
    private Long courseId;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private Integer status;
    private Integer createdBy;

}
