package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.RegisterValidateRequest;
import com.udemy.elearning.models.User;
import com.udemy.elearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);

    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "page",defaultValue = "0") Integer page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<User> pages = this.userService.getUsers(pageable);
        return ResponseEntity.ok(pages.getContent());
    }

    @GetMapping("/validate")
    public Boolean validate(@RequestBody() RegisterValidateRequest registerValidateRequest ) {
        if(Objects.equals(registerValidateRequest.getKeyValidate(), "email")){
            return this.userService.validateEmail(registerValidateRequest.getValueValidate());
        }else if(Objects.equals(registerValidateRequest.getKeyValidate(), "username")){
            return this.userService.validateUsername(registerValidateRequest.getValueValidate());
        }else if(Objects.equals(registerValidateRequest.getKeyValidate(), "phoneNumber")){
            return this.userService.validatePhoneNumber(registerValidateRequest.getValueValidate());
        }else{
            return true;
        }
    }
}
