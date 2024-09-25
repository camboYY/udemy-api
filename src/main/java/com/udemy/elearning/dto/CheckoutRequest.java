package com.udemy.elearning.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CheckoutRequest {
    private Long userId;
    private double totalAmount;

    private Number CardNumber;
    private String CardHolderName;
    private String CardType;
    private Date CardExpiry;
    private String CardCVC;

    private ArrayList<Long> courseId;

}
