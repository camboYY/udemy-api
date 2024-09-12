package com.udemy.elearning.mapper;
import com.udemy.elearning.models.CourseTags;
import lombok.Data;

@Data
public class CourseTagResponse {
    private String title;
    private Long courseId;
    private long id;

    public CourseTagResponse(CourseTags courseTags){
        this.setTitle(courseTags.getTitle());
        this.setCourseId(courseTags.getCourseId());
        this.setId(courseTags.getId());
    }
}
