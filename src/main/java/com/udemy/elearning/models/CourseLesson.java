package com.udemy.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
    @ColumnDefault("false") // false : meaning not have any progress , true yes progressing watch a video
    private Boolean progressing = false;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
