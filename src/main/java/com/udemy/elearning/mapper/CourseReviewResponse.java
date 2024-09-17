package com.udemy.elearning.mapper;
import com.udemy.elearning.models.CourseReview;
import lombok.Data;

@Data
public class CourseReviewResponse {
    private String comment;
    private Long courseId;
    private Long userId;
    private Double rating;
    private long id;

    public CourseReviewResponse(CourseReview courseReview){
        this.setComment(courseReview.getComments());
        this.setCourseId(courseReview.getCourseId());
        this.setUserId(courseReview.getUserId());
        this.setRating(courseReview.getRating());
        this.setId(courseReview.getId());
    }
}
