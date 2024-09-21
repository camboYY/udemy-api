package com.udemy.elearning.dto;

import lombok.Data;

@Data
public class RegisterValidateRequest {
    //keyValidate will be username or email or phoneNumber
    private String keyValidate;
    private String valueValidate;
}
