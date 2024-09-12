package com.udemy.elearning.controllers;

import com.udemy.elearning.models.User;
import com.udemy.elearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "page",defaultValue = "0") Integer page) {
        Integer size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<User> pages = this.userService.getUsers(pageable);
        return ResponseEntity.ok(pages.getContent());
    }
}
