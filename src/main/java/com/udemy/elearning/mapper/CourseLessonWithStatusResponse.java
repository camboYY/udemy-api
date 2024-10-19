package com.udemy.elearning.mapper;
import com.udemy.elearning.models.CourseLesson;
import lombok.Data;

import javax.swing.text.StyledEditorKit;

@Data
public class CourseLessonWithStatusResponse {
    private String title;
    private Long courseId;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private Integer status;
    private Integer createdBy;
    private Boolean isCompleted;
    private long id;

    public CourseLessonWithStatusResponse(CourseLesson courseLessons, Boolean isCompleted){
        this.setTitle(courseLessons.getTitle());
        this.setCourseId(courseLessons.getCourseId());
        this.setDescription(courseLessons.getDescription());
        this.setThumbnailUrl(courseLessons.getThumbnailUrl());
        this.setVideoUrl(courseLessons.getVideoUrl());
        this.setStatus(courseLessons.getStatus());
        this.setCreatedBy(courseLessons.getCreatedBy());
        this.setIsCompleted(isCompleted);
        this.setId(courseLessons.getId());
    }
}
