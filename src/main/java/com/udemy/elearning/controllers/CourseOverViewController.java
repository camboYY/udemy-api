package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseOverViewRequest;
import com.udemy.elearning.mapper.CourseOverViewResponse;
import com.udemy.elearning.models.CourseOverView;
import com.udemy.elearning.services.CourseOverViewService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courseOverViews")
public class CourseOverViewController {

    private final CourseOverViewService courseOverViewService;

    public CourseOverViewController(CourseOverViewService courseOverViewService) {
        this.courseOverViewService = courseOverViewService;
    }

    @PostMapping()
    public ResponseEntity<CourseOverViewResponse> create(@Valid @RequestBody CourseOverViewRequest courseOverViewRequest) throws BadRequestException {
        CourseOverView courseOverViewCreate = courseOverViewService.create(courseOverViewRequest);
        CourseOverViewResponse courseOverViewResponse = new CourseOverViewResponse(courseOverViewCreate);
        return ResponseEntity.ok(courseOverViewResponse);
    }

    @GetMapping("/getByCourseId/{courseId}")
    public ResponseEntity<List<CourseOverView>> getByCourseId(@PathVariable(value = "courseId") Long courseId) {
        List<CourseOverView> courseOverViewsList = courseOverViewService.findByCourseId(courseId);
        return ResponseEntity.ok(courseOverViewsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseOverView> getById(@PathVariable(value = "id") Long id) {
        CourseOverView courseOverViews = courseOverViewService.findById(id);
        return ResponseEntity.ok(courseOverViews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseOverViewResponse> updateCourseOverViews(@PathVariable Long id, @RequestBody CourseOverViewRequest courseOverViewRequest) {
        CourseOverView courseOverViews = courseOverViewService.updateCourseOverViews(id,courseOverViewRequest);
        CourseOverViewResponse courseOverViewResponse = new CourseOverViewResponse(courseOverViews);
        return ResponseEntity.ok(courseOverViewResponse);
    }

}
