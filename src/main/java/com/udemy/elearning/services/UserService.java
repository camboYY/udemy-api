package com.udemy.elearning.services;

import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.mapper.CourseByResponse;
import com.udemy.elearning.models.ERole;
import com.udemy.elearning.models.Role;
import com.udemy.elearning.models.User;
import com.udemy.elearning.repository.RoleRepository;
import com.udemy.elearning.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getUsers(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public CourseByResponse findById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
        return new CourseByResponse(user);
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
    public User getUserReview(Long id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
    }

    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User createAdministrator(SignupRequest input) {
        Optional<Role> optionalRole = roleRepository.findByName(ERole.ROLE_ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setName(input.getName());
        user  .setEmail(input.getEmail());
        user    .setPassword(passwordEncoder.encode(input.getPassword()));
        user    .setRole(optionalRole.get());

        return userRepository.save(user);

    }
}
