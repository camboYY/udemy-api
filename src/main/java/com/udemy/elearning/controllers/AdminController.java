package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.LoginRequest;
import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.dto.UpgradeRoleRequest;
import com.udemy.elearning.mapper.*;
import com.udemy.elearning.models.Checkout;
import com.udemy.elearning.models.User;
import com.udemy.elearning.services.AuthenticationService;
import com.udemy.elearning.services.CheckoutService;
import com.udemy.elearning.services.JwtService;
import com.udemy.elearning.services.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody SignupRequest registerUserDto) {
        logger.info("SignupRequest : {} ",registerUserDto);
        User createdAdmin = userService.createAdministrator(registerUserDto);
        return ResponseEntity.ok(createdAdmin);
    }

    @PostMapping("/login")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<JwtResponse> adminLogin(@Valid @RequestBody LoginRequest loginUserDto) {
        logger.info("loginUserDto{}", loginUserDto);

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        JwtResponse loginResponse = new JwtResponse (jwtToken,jwtService.getExpirationTime(),authenticatedUser.getId());

        return ResponseEntity.ok(loginResponse);
    }

    // amdin user updates user's role to be teacher/author
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/upgradeRole")
    public ResponseEntity<UpgradeRoleResponse> requestToUpgradeRole (@Valid @RequestBody UpgradeRoleRequest roleRequest) {
        UpgradeRoleResponse upgradeRoleResponse =  userService.upgradeRole(roleRequest);
        return  ResponseEntity.ok(upgradeRoleResponse);
    }

    // get list of  user requesting new role to be author
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/getListOfUserRequestingNewRole")
    public ResponseEntity<List<User>> getListOfUserRequestingNewRole () {
        List<User> userList =  userService.getListOfUserRequestingNewRole();
        return  ResponseEntity.ok(userList);
    }

    // get list of purchasing courses
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_TEACHER')")
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
}
