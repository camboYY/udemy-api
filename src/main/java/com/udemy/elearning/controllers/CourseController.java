package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.mapper.CourseResponse;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.services.CategoryService;
import com.udemy.elearning.services.CourseService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest courseRequest) throws BadRequestException {
        Course courseCreate = courseService.create(courseRequest);
        Category category = categoryService.findById(courseCreate.getCategoryId());
        CourseResponse courseResponse = new CourseResponse(courseCreate,category);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<CourseResponse>> getAll(@PathVariable(value = "page") int page) {
        List<Course> courseList = courseService.findAll(page);
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courseList) {
                Category category = categoryService.findById(course.getCategoryId());
            courseResponseList.add(new CourseResponse(course,category));
        }
        return ResponseEntity.ok(courseResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable(value = "id") Long id) {
        Course course = courseService.findById(id);
        Category category = categoryService.findById(course.getCategoryId());
        CourseResponse courseResponse = new CourseResponse(course,category);
        return ResponseEntity.ok(courseResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        Course course = courseService.updateCourse(id,courseRequest);
        Category category = categoryService.findById(course.getCategoryId());
        CourseResponse courseResponse = new CourseResponse(course,category);
        return ResponseEntity.ok(courseResponse);
    }

}
