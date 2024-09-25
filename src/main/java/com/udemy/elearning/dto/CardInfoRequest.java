package com.udemy.elearning.dto;

import lombok.Data;

@Data
public class CardInfoRequest {

    private String CardNumber;
    private String CardHolderName;
    private String CardType;
    private String CardExpiry;
    private String CardCVC;

}
