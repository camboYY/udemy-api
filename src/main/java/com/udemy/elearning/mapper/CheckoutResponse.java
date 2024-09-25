package com.udemy.elearning.mapper;
import com.udemy.elearning.models.Checkout;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutResponse {
    private Long userId;
    private double totalAmount;
    private long id;

    public CheckoutResponse(Checkout checkout){
        this.setUserId(checkout.getUserId());
        this.setTotalAmount(checkout.getTotalAmount());
        this.setId(checkout.getId());
    }
}
