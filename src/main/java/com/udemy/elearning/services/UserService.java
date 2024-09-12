package com.udemy.elearning.services;

import com.udemy.elearning.models.User;
import com.udemy.elearning.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }
}
