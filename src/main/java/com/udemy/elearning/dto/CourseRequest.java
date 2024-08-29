package com.udemy.elearning.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class CourseRequest {
    private  String title;
    private Double price;
    private Integer courseBy;
    private String courseInclude;
    private  String courseLearning;
    private Integer status;
    private Integer createdBy;
    private Date createdAt;
    private Date updatedAt;

}
