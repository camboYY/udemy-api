package com.udemy.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "CourseOverView")
@Entity
public class CourseOverView {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String comments;

    private double rating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
