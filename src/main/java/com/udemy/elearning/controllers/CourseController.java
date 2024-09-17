package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.dto.CourseSearchRequest;
import com.udemy.elearning.mapper.CourseByResponse;
import com.udemy.elearning.mapper.CourseResponse;
import com.udemy.elearning.models.*;
import com.udemy.elearning.services.*;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;
    private final CourseTagService courseTagService;
    private final CourseLessonService courseLessonService;
    private final CourseReviewService courseReviewService;
    private final UserService userService;

    public CourseController(CourseService courseService, CategoryService categoryService, CourseTagService courseTagService, CourseLessonService courseLessonService, CourseReviewService courseReviewService, UserService userService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.courseTagService = courseTagService;
        this.courseLessonService = courseLessonService;
        this.courseReviewService = courseReviewService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest courseRequest) throws BadRequestException {
        Course courseCreate = courseService.create(courseRequest);
        CourseResponse courseResponse = buildCourseResponse(courseCreate);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<CourseResponse>> searchByString(@RequestBody(required = false) CourseSearchRequest courseSearchRequest, @PathVariable(value = "page") int page) {
        if (courseSearchRequest.getKeyword() == null || courseSearchRequest.getKeyword().isEmpty()) {
            List<Course> courseList = courseService.findAll(page);
            List<CourseResponse> courseResponseList = new ArrayList<>();
            for (Course course : courseList) {
                courseResponseList.add(buildCourseResponse(course));
            }
            return ResponseEntity.ok(courseResponseList);
        }else{
            PageRequest pageRequest = PageRequest.of(page-1, 10);
            List<Course> courseList = courseService.searchByString(courseSearchRequest.getKeyword(), pageRequest);
            List<CourseResponse> courseResponseList = new ArrayList<>();
            for (Course course : courseList) {
                courseResponseList.add(buildCourseResponse(course));
            }
            return ResponseEntity.ok(courseResponseList);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable(value = "id") Long id) {
        Course course = courseService.findById(id);
        CourseResponse courseResponse = buildCourseResponse(course);
        return ResponseEntity.ok(courseResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        Course course = courseService.updateCourse(id,courseRequest);
        CourseResponse courseResponse = buildCourseResponse(course);
        return ResponseEntity.ok(courseResponse);
    }
    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity<List<CourseResponse>> getByCategoryId(@PathVariable(value = "id") Long id) {
        List<Course> courseList = courseService.findByCategoryId(id);
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courseList) {
            courseResponseList.add(buildCourseResponse(course));
        }
        return ResponseEntity.ok(courseResponseList);
    }
    private CourseResponse buildCourseResponse(Course course) {
        Category category = categoryService.findById(course.getCategoryId());
        List<CourseTags> courseTagsList = courseTagService.findByCourseId(course.getId());
        List<CourseLesson> courseLessonList = courseLessonService.findByCourseId(course.getId());
        List<CourseReview> courseReviews = courseReviewService.findByCourseId(course.getId());
        CourseByResponse courseByResponse = userService.findById(Long.valueOf(course.getCourseBy()));
        double totalRating = 0.0;
        for (CourseReview review : courseReviews) {
            totalRating += review.getRating();
        }
        double averageRating = courseReviews.isEmpty() ? 0.0 : totalRating / courseReviews.size();
        return new CourseResponse(course, category, courseByResponse, averageRating, courseTagsList, courseLessonList, courseReviews);
    }
}
