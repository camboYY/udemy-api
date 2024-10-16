package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.LoginRequest;
import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.dto.UpgradeRoleRequest;
import com.udemy.elearning.mapper.*;
import com.udemy.elearning.models.*;
import com.udemy.elearning.services.*;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/admins")
@RestController
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseTagService courseTagService;
    @Autowired
    private CourseLessonService courseLessonService;
    @Autowired
    private CourseReviewService courseReviewService;


    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<User> createAdministrator(@RequestBody SignupRequest registerUserDto) {
        logger.info("SignupRequest : {} ",registerUserDto);
        User createdAdmin = userService.createAdministrator(registerUserDto);
        return ResponseEntity.ok(createdAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> adminLogin(@Valid @RequestBody LoginRequest loginUserDto) throws BadRequestException {
        logger.info("loginUserDto{}", loginUserDto);

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
       boolean userExist = userService.checkIfUserApplicableRole(authenticatedUser.getUsername());
        if(userExist) {
            JwtResponse loginResponse = new JwtResponse(jwtToken, jwtService.getExpirationTime(), authenticatedUser.getId());

            return ResponseEntity.ok(loginResponse);
        } else {
            throw new BadRequestException("You are not allowed to login");
        }
    }

    // amdin user updates user's role to be teacher/author
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @PostMapping("/upgradeRole")
    public ResponseEntity<UpgradeRoleResponse> requestToUpgradeRole (@Valid @RequestBody UpgradeRoleRequest roleRequest) {
        UpgradeRoleResponse upgradeRoleResponse =  userService.upgradeRole(roleRequest);
        return  ResponseEntity.ok(upgradeRoleResponse);
    }

    // get list of  user requesting new role to be author
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/getListOfUserRequestingNewRole")
    public ResponseEntity<List<User>> getListOfUserRequestingNewRole () {
        List<User> userList =  userService.getListOfUserRequestingNewRole();
        return  ResponseEntity.ok(userList);
    }

    // get list of purchasing courses
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @GetMapping("/getPurchaseList/page/{page}")
    public ResponseEntity<List<CheckoutAdminResponse>> getAll(@PathVariable(value = "page") int page) {
        List<Checkout> checkoutList = checkoutService.findAll(page);
        List<CheckoutAdminResponse> checkoutAdminResponseList = new ArrayList<>();
        for (Checkout checkout : checkoutList){
            CourseByResponse courseByResponse  = userService.findById(checkout.getUserId());
            CheckoutAdminResponse checkoutAdminResponse = new CheckoutAdminResponse(checkout,courseByResponse.getName());
            checkoutAdminResponseList.add(checkoutAdminResponse);
        }
        return ResponseEntity.ok(checkoutAdminResponseList);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN','ROLE_TEACHER')")
    @GetMapping("/searchByStringWithAuthor/page/{page}")
    public ResponseEntity<List<CourseResponse>> searchByStringWithAuthor(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") int page) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            PageRequest pageRequest = PageRequest.of(page-1, 10);
            List<Course> courseList = courseService.searchByStringWithAuthor(keyword,currentUser.getId(), pageRequest);
            List<CourseResponse> courseResponseList = new ArrayList<>();
            for (Course course : courseList) {
                courseResponseList.add(buildCourseResponse(course));
            }
            return ResponseEntity.ok(courseResponseList);
    }
    public CourseResponse buildCourseResponse(Course course) {
        Category category = categoryService.findById(course.getCategoryId());
        List<CourseTags> courseTagsList = courseTagService.findByCourseId(course.getId());
        List<CourseLesson> courseLessonList = courseLessonService.findByCourseIdSorted(course.getId());
        List<CourseReview> courseReviewList = courseReviewService.findByCourseId(course.getId());
        CourseByResponse courseByResponse = userService.findById(Long.valueOf(course.getCourseBy()));
        double totalRating = 0.0;
        List<CourseReviewResponse> courseReviewResponses = new ArrayList<>();
        for (CourseReview reviewResponse : courseReviewList) {
            totalRating += reviewResponse.getRating();
            courseReviewResponses.add(buildCourseReviewResponse(reviewResponse));
        }
        double averageRatingOrig = courseReviewList.isEmpty() ? 0.0 : totalRating / courseReviewList.size();
        double averageRating = Math.round(averageRatingOrig * 100.0) / 100.0;
        return new CourseResponse(course, category, courseByResponse, averageRating, courseTagsList, courseLessonList, courseReviewResponses);
    }
    private CourseReviewResponse buildCourseReviewResponse(CourseReview courseReview) {
        User user = userService.getUserReview(courseReview.getUserId());
        String username = user.getName();
        return new CourseReviewResponse(courseReview, username);
    }
}
