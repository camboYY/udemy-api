package com.udemy.elearning.mapper;
import com.udemy.elearning.models.Course;
import lombok.Data;

import java.util.Date;

@Data
public class CourseResponse {
    private  String title;
    private Double price;
    private Integer courseBy;
    private String courseInclude;
    private  String courseLearning;
    private Integer status;
    private Integer categoryId;
    private Integer createdBy;
    private Date createdAt;
    private Date updatedAt;
    private long id;

    public CourseResponse(Course course){
        this.setTitle(course.getTitle());
        this.setPrice(course.getPrice());
        this.setCourseBy(course.getCourseBy());
        this.setCourseInclude(course.getCourseInclude());
        this.setCourseLearning(course.getCourseLearning());
        this.setStatus(course.getStatus());
        this.setCategoryId(course.getCategoryId());
        this.setCreatedBy(course.getCreatedBy());
        this.setCreatedAt(course.getCreatedAt());
        this.setUpdatedAt(course.getUpdatedAt());
        this.setId(course.getId());

    }
}
