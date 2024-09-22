package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseReviewRequest;
import com.udemy.elearning.mapper.CourseByResponse;
import com.udemy.elearning.mapper.CourseResponse;
import com.udemy.elearning.mapper.CourseReviewResponse;
import com.udemy.elearning.models.*;
import com.udemy.elearning.services.CourseReviewService;
import com.udemy.elearning.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courseReviews")
public class CourseReviewController {

    private final CourseReviewService courseReviewService;
    private final UserService userService;

    public CourseReviewController(CourseReviewService courseReviewService, UserService userService) {
        this.courseReviewService = courseReviewService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<CourseReviewResponse> create(@Valid @RequestBody CourseReviewRequest courseReviewRequest) throws BadRequestException {
        CourseReview courseReviewCreate = courseReviewService.create(courseReviewRequest);
        CourseReviewResponse courseReviewResponse = buildCourseReviewResponse(courseReviewCreate);
        return ResponseEntity.ok(courseReviewResponse);
    }

    @GetMapping("/getByCourseId/{courseId}")
    public ResponseEntity<List<CourseReview>> getByCourseId(@PathVariable(value = "courseId") Long courseId) {
        List<CourseReview> courseReviewList = courseReviewService.findByCourseId(courseId);
        return ResponseEntity.ok(courseReviewList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReviewResponse> getById(@PathVariable(value = "id") Long id) {
        CourseReview courseReviews = courseReviewService.findById(id);
        CourseReviewResponse courseReviewResponse = buildCourseReviewResponse(courseReviews);
        return ResponseEntity.ok(courseReviewResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseReviewResponse> updateCourseReviews(@PathVariable Long id, @RequestBody CourseReviewRequest courseReviewRequest) {
        CourseReview courseReviews = courseReviewService.updateCourseReviews(id, courseReviewRequest);
        CourseReviewResponse courseReviewResponse = buildCourseReviewResponse(courseReviews);
        return ResponseEntity.ok(courseReviewResponse);
    }

    private CourseReviewResponse buildCourseReviewResponse(CourseReview courseReview) {
        User user = userService.getUserReview(courseReview.getUserId());
        String username = user.getName();
        return new CourseReviewResponse(courseReview, username);
    }

}
