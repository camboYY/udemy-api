package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseReviewRequest;
import com.udemy.elearning.mapper.CourseReviewResponse;
import com.udemy.elearning.models.CourseReview;
import com.udemy.elearning.services.CourseReviewService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courseReviews")
public class CourseReviewController {

    private final CourseReviewService courseReviewService;

    public CourseReviewController(CourseReviewService courseReviewService) {
        this.courseReviewService = courseReviewService;
    }

    @PostMapping()
    public ResponseEntity<CourseReviewResponse> create(@Valid @RequestBody CourseReviewRequest courseReviewRequest) throws BadRequestException {
        CourseReview courseReviewCreate = courseReviewService.create(courseReviewRequest);
        CourseReviewResponse courseReviewResponse = new CourseReviewResponse(courseReviewCreate);
        return ResponseEntity.ok(courseReviewResponse);
    }

    @GetMapping("/getByCourseId/{courseId}")
    public ResponseEntity<List<CourseReview>> getByCourseId(@PathVariable(value = "courseId") Long courseId) {
        List<CourseReview> courseReviewsList = courseReviewService.findByCourseId(courseId);
        return ResponseEntity.ok(courseReviewsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReview> getById(@PathVariable(value = "id") Long id) {
        CourseReview courseReviews = courseReviewService.findById(id);
        return ResponseEntity.ok(courseReviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseReviewResponse> updateCourseReviews(@PathVariable Long id, @RequestBody CourseReviewRequest courseReviewRequest) {
        CourseReview courseReviews = courseReviewService.updateCourseReviews(id, courseReviewRequest);
        CourseReviewResponse courseReviewResponse = new CourseReviewResponse(courseReviews);
        return ResponseEntity.ok(courseReviewResponse);
    }

}
