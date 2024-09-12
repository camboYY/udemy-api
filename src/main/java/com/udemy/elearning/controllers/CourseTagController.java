package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.dto.CourseTagRequest;
import com.udemy.elearning.mapper.CourseTagResponse;
import com.udemy.elearning.models.CourseTags;
import com.udemy.elearning.services.CourseTagService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courseTags")
public class CourseTagController {

    private final CourseTagService courseTagService;

    public CourseTagController(CourseTagService courseTagService) {
        this.courseTagService = courseTagService;
    }

    @PostMapping()
    public ResponseEntity<CourseTagResponse> create(@Valid @RequestBody CourseTagRequest courseTagRequest) throws BadRequestException {
        CourseTags courseTagCreate = courseTagService.create(courseTagRequest);
        CourseTagResponse courseTagResponse = new CourseTagResponse(courseTagCreate);
        return ResponseEntity.ok(courseTagResponse);
    }

    @GetMapping("/getByCourseId/{courseId}")
    public ResponseEntity<List<CourseTags>> getByCourseId(@PathVariable(value = "courseId") Long courseId) {
        List<CourseTags> courseTagsList = courseTagService.findByCourseId(courseId);
        return ResponseEntity.ok(courseTagsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseTags> getById(@PathVariable(value = "id") Long id) {
        CourseTags courseTags = courseTagService.findById(id);
        return ResponseEntity.ok(courseTags);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseTagResponse> updateCourseTags(@PathVariable Long id, @RequestBody CourseTagRequest courseTagRequest) {
        CourseTags courseTags = courseTagService.updateCourseTags(id,courseTagRequest);
        CourseTagResponse courseTagResponse = new CourseTagResponse(courseTags);
        return ResponseEntity.ok(courseTagResponse);
    }

}
