package com.udemy.elearning.mapper;
import com.udemy.elearning.models.*;
import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {
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
    private List<CourseLesson> courseLessons;
    private List<CourseReviewResponse> courseReviewResponses;

    public CourseResponse(Course course, Category category,CourseByResponse courseBy, Double rating, List<CourseTags> courseTagsList,List<CourseLesson> courseLessons, List<CourseReviewResponse> courseReviewResponses) {
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
        this.setCourseLessons(courseLessons);
        this.setCourseReviewResponses(courseReviewResponses);
    }
}
