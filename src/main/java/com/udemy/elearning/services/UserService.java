package com.udemy.elearning.services;

import com.udemy.elearning.mapper.CourseByResponse;
import com.udemy.elearning.mapper.UserResponse;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.models.User;
import com.udemy.elearning.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public CourseByResponse findById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
        CourseByResponse courseByResponse = new CourseByResponse(user);
        return courseByResponse;
    }
    public Boolean validateEmail(String keyValue){
        return userRepository.existsByEmail(keyValue);
    }
    public Boolean validateUsername(String keyValue){
        return userRepository.existsByUsername(keyValue);
    }
    public Boolean validatePhoneNumber(String keyValue){
        return userRepository.existsByPhoneNumber(keyValue);
    }
}
