package com.udemy.elearning.dto;

import lombok.Data;

@Data
public class CheckoutCourseRequest {

    private Long checkoutId;
    private Long courseId;
    private double price;

}
