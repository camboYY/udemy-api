package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.mapper.CourseResponse;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.services.CourseService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest courseRequest) throws BadRequestException {
        Course courseCreate = courseService.create(courseRequest);
        CourseResponse courseResponse = new CourseResponse(courseCreate);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<Course>> getAll(@PathVariable(value = "page") int page) {
        List<Course> courseList = courseService.findAll(page);
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable(value = "id") Long id) {
        Course course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        Course course = courseService.updateCourse(id,courseRequest);
        CourseResponse courseResponse = new CourseResponse(course);
        return ResponseEntity.ok(courseResponse);
    }

}
