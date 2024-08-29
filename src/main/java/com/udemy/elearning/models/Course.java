package com.udemy.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Integer createdBy;
    @Column()
    private Date createdAt;
    @Column()
    private Date updatedAt;

    public Course(
            String title,
            Integer price,
            Integer courseBy,
    ) {
        this.name = name;
        this.parentId = parentId;
    }

}
