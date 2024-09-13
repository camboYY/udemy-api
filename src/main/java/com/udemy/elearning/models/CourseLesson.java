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
@Table(name = "CourseLessons")
@Entity
public class CourseLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private Long courseId;
    @Column()
    private String title;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private String thumbnailUrl;
    @Column(nullable = true)
    private String videoUrl;
    @Column()
    private Integer status;
    @Column()
    private Integer createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
