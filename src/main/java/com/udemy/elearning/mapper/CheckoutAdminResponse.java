package com.udemy.elearning.mapper;
import com.udemy.elearning.models.Checkout;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckoutAdminResponse {
    private long id;
    private String name;
    private double totalAmount;
    private LocalDateTime createdAt;

    public CheckoutAdminResponse(Checkout checkout,String name){
        this.setId(checkout.getId());
        this.setCreatedAt(checkout.getCreatedAt());
        this.setName(name);
        this.setTotalAmount(checkout.getTotalAmount());
    }
}
