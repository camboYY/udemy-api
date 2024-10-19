package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CheckoutRequest;
import com.udemy.elearning.mapper.*;
import com.udemy.elearning.models.*;
import com.udemy.elearning.services.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private static final Logger logger = LogManager.getLogger(CheckoutService.class);
    private final CheckoutService checkoutService;
    private final CourseService courseService;
    private final CheckoutCourseService checkoutCourseService;
    private final CategoryService categoryService;
    private final CourseTagService courseTagService;
    private final CourseReviewService courseReviewService;
    private final CourseLessonService courseLessonService;
    private final UserService userService;

    public CheckoutController(CheckoutService checkoutService, CourseService courseService, CheckoutCourseService checkoutCourseService, CategoryService categoryService, CourseTagService courseTagService, CourseReviewService courseReviewService, CourseLessonService courseLessonService, UserService userService) {
        this.checkoutService = checkoutService;
        this.courseService = courseService;
        this.checkoutCourseService = checkoutCourseService;
        this.categoryService = categoryService;
        this.courseTagService = courseTagService;
        this.courseReviewService = courseReviewService;
        this.courseLessonService = courseLessonService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Checkout> create(@RequestBody CheckoutRequest checkoutRequest) throws BadRequestException {
        Checkout checkout = checkoutService.create(checkoutRequest);
        return ResponseEntity.ok(checkout);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkout> getById(@PathVariable(value = "id") Long id) {
        Checkout checkout = checkoutService.findById(id);
        return ResponseEntity.ok(checkout);
    }

    @GetMapping("/myOrderList/{userId}")
    public ResponseEntity<List<CourseAfterCheckoutResponse>> myOrderList(@PathVariable(value = "userId") Long userId) {
        List<Checkout> checkoutList = checkoutService.myOrderList(userId);
        List<CourseAfterCheckoutResponse> courseResponseList = new ArrayList<>();
        for (Checkout checkout : checkoutList) {
            List<CheckoutCourse> courseList = checkoutCourseService.findByCheckoutId(checkout.getId());
            for (CheckoutCourse checkoutCourse : courseList) {
                Course course = courseService.findById(checkoutCourse.getCourseId());
                courseResponseList.add(buildCourseResponse(course, userId));
            }
        }
        return ResponseEntity.ok(courseResponseList);
    }

    private CourseAfterCheckoutResponse buildCourseResponse(Course course, Long userId) {
        Category category = categoryService.findById(course.getCategoryId());
        List<CourseTags> courseTagsList = courseTagService.findByCourseId(course.getId());
        List<CourseLesson> courseLessonList = courseLessonService.findByCourseIdSorted(course.getId());
        List<CourseReview> courseReviewList = courseReviewService.findByCourseId(course.getId());
        CourseByResponse courseByResponse = userService.findById(userId);
        logger.info("courseByResponse {}", courseByResponse);
        double totalRating = 0.0;
        List<CourseReviewResponse> courseReviewResponses = new ArrayList<>();
        for (CourseReview reviewResponse : courseReviewList) {
            totalRating += reviewResponse.getRating();
            courseReviewResponses.add(buildCourseReviewResponse(reviewResponse));
        }
        List<CourseLessonWithStatusResponse> courseLessonWithStatusResponseList = new ArrayList<>();
        for (CourseLesson courseLessonData : courseLessonList) {
            Integer countCompleted = courseLessonService.findByCourseIdWithUserId(courseLessonData.getId(),userId);
            boolean b = countCompleted > 0;
            CourseLessonWithStatusResponse courseLessonWithStatusResponse = new CourseLessonWithStatusResponse(courseLessonData, b);
            courseLessonWithStatusResponseList.add(courseLessonWithStatusResponse);
        }
        double averageRatingOrig = courseReviewList.isEmpty() ? 0.0 : totalRating / courseReviewList.size();
        double averageRating = Math.round(averageRatingOrig * 100.0) / 100.0;
        return new CourseAfterCheckoutResponse(course, category, courseByResponse, averageRating, courseTagsList, courseLessonWithStatusResponseList, courseReviewResponses);
    }
    private CourseReviewResponse buildCourseReviewResponse(CourseReview courseReview) {
        User user = userService.getUserReview(courseReview.getUserId());
        String username = user.getName();
        return new CourseReviewResponse(courseReview, username);
    }
}
