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
    private CourseController courseController;
    @Autowired
    private UserController userController;


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
            User user   = userController.authenticatedUser().getBody();
            PageRequest pageRequest = PageRequest.of(page-1, 10);
            List<Course> courseList = courseService.searchByStringWithAuthor(keyword,user.getId(), pageRequest);
            List<CourseResponse> courseResponseList = new ArrayList<>();
            for (Course course : courseList) {
                courseResponseList.add(courseController.buildCourseResponse(course));
            }
            return ResponseEntity.ok(courseResponseList);
    }
}
