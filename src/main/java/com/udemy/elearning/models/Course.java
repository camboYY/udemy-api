package com.udemy.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Courses")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column()
    private  String title;
    @Column()
    private Double price;
    @Column()
    private Integer courseBy;
    @Column(nullable = true)
    private String courseInclude;
    @Column(nullable = true)
    private  String courseLearning;
    @Column()
    private Integer status;
    @Column()
    private Long categoryId;
    @Column()
    private Integer createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
