package com.udemy.elearning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileRequest {

    @NotBlank
    @NotNull
    private long userId;

    private String avatar;

    private String nickName;

    @NotBlank
    private String workExperience;

    private String currentWorkPlace;
}
