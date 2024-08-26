package com.udemy.elearning.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileRequest {

    private String avatar;

    private String nickName;

    @NotBlank
    private String workExperience;

    private String currentWorkPlace;
}
