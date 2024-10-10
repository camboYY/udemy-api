package com.udemy.elearning.mapper;

import com.udemy.elearning.models.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UpgradeRoleResponse {
    private ERole name;
}
