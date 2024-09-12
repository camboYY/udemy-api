package com.udemy.elearning.mapper;
import com.udemy.elearning.models.CourseLesson;
import lombok.Data;

@Data
public class CourseLessonResponse {
    private String title;
    private Long courseId;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private Integer status;
    private Integer createdBy;
    private long id;

    public CourseLessonResponse(CourseLesson courseLessons){
        this.setTitle(courseLessons.getTitle());
        this.setCourseId(courseLessons.getCourseId());
        this.setDescription(courseLessons.getDescription());
        this.setThumbnailUrl(courseLessons.getThumbnailUrl());
        this.setVideoUrl(courseLessons.getVideoUrl());
        this.setStatus(courseLessons.getStatus());
        this.setCreatedBy(courseLessons.getCreatedBy());
        this.setId(courseLessons.getId());
    }
}
