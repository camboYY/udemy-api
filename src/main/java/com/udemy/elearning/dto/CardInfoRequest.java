package com.udemy.elearning.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CardInfoRequest {

    private String cardNumber;
    private String cardHolderName;
    private String cardType;
    private String cardExpiry;
    private long cardCVC;

}
