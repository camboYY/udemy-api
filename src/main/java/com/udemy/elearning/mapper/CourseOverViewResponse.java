package com.udemy.elearning.mapper;
import com.udemy.elearning.models.CourseLesson;
import com.udemy.elearning.models.CourseOverView;
import lombok.Data;

@Data
public class CourseOverViewResponse {
    private String comment;
    private Long courseId;
    private Long userId;
    private Double rating;
    private long id;

    public CourseOverViewResponse(CourseOverView courseOverView){
        this.setComment(courseOverView.getComments());
        this.setCourseId(courseOverView.getCourseId());
        this.setUserId(courseOverView.getUserId());
        this.setRating(courseOverView.getRating());
        this.setId(courseOverView.getId());
    }
}
