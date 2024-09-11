package com.udemy.elearning.mapper;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.models.Course;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class CourseResponse {
    private  String title;
    private Double price;
    private Integer courseBy;
    private String courseInclude;
    private  String courseLearning;
    private Integer status;
    private Integer createdBy;
    private long id;
    private Category category;

    public CourseResponse(Course course, Category category) {
        this.setTitle(course.getTitle());
        this.setPrice(course.getPrice());
        this.setCourseBy(course.getCourseBy());
        this.setCourseInclude(course.getCourseInclude());
        this.setCourseLearning(course.getCourseLearning());
        this.setStatus(course.getStatus());
        this.setCreatedBy(course.getCreatedBy());
        this.setId(course.getId());
        this.setCategory(category);
    }
}
