package com.udemy.elearning.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpgradingRoleRequest {
    @NotNull
    private long profileId;
}
