package com.udemy.elearning.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryRequest {

    private String name;
    private Integer parentId;

}
