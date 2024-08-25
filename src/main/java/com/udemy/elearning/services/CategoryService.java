package com.udemy.elearning.services;


import com.udemy.elearning.dto.CategoryRequest;
import com.udemy.elearning.dto.LoginRequest;
import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.models.ERole;
import com.udemy.elearning.models.Role;
import com.udemy.elearning.models.User;
import com.udemy.elearning.repository.CategoryRepository;
import com.udemy.elearning.repository.RoleRepository;
import com.udemy.elearning.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;


    public CategoryService(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(CategoryRequest categoryRequest) throws BadRequestException {
        logger.info("CategoryRequest{}", categoryRequest);
        // Create new user's account
        Category category = new Category(categoryRequest.getName(), categoryRequest.getParentId());
        categoryRepository.save(category);
        return categoryRepository.save(category);
    }

}
