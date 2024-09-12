package com.udemy.elearning.dto;
import lombok.Data;


@Data
public class CourseRequest {
    private  String title;
    private Double price;
    private Integer courseBy;
    private String courseInclude;
    private  String courseLearning;
    private Integer status;
    private Long categoryId;
    private Integer createdBy;

}
