package com.udemy.elearning.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CheckoutRequest {
    private Long userId;
    private Double totalAmount;
    private String cardNumber;
    private String cardHolderName;
    private String cardType;
    private String cardExpiry;
    private long cardCVC;

    private List<Long> courseId;

}
