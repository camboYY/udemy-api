package com.udemy.elearning.mapper;

import com.udemy.elearning.models.Category;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.models.CourseLesson;
import com.udemy.elearning.models.CourseTags;
import lombok.Data;

import java.util.List;

@Data
public class CourseAfterCheckoutResponse {
    private  String title;
    private Double price;
    private String courseInclude;
    private  String courseLearning;
    private  String thumbnailUrl;
    private Integer status;
    private Integer createdBy;
    private long id;
    private CourseByResponse courseBy;
    private Double rating;
    private Category category;
    private List<CourseTags> courseTags;
    private List<CourseLessonWithStatusResponse> courseLessonWithStatusResponses;
    private List<CourseReviewResponse> courseReviewResponses;

    public CourseAfterCheckoutResponse(Course course, Category category, CourseByResponse courseBy, Double rating, List<CourseTags> courseTagsList, List<CourseLessonWithStatusResponse> courseLessonWithStatusResponses, List<CourseReviewResponse> courseReviewResponses) {
        this.setTitle(course.getTitle());
        this.setPrice(course.getPrice());
        this.setCourseInclude(course.getCourseInclude());
        this.setCourseLearning(course.getCourseLearning());
        this.setThumbnailUrl(course.getThumbnailUrl());
        this.setStatus(course.getStatus());
        this.setCreatedBy(course.getCreatedBy());
        this.setId(course.getId());
        this.setCourseBy(courseBy);
        this.setRating(rating);
        this.setCategory(category);
        this.setCourseTags(courseTagsList);
        this.setCourseLessonWithStatusResponses(courseLessonWithStatusResponses);
        this.setCourseReviewResponses(courseReviewResponses);
    }
}
