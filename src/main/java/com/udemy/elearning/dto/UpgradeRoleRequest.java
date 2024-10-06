package com.udemy.elearning.dto;

import com.udemy.elearning.models.ERole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpgradeRoleRequest {
    @NotBlank
    @NotNull
    private ERole role;

    @NotBlank
    @NotNull
    private  long userId;
}
