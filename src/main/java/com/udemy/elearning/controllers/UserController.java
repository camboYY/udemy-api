package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.RegisterValidateRequest;
import com.udemy.elearning.models.User;
import com.udemy.elearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
