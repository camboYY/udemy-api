package com.udemy.elearning.services;


import com.udemy.elearning.dto.CategoryRequest;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.repository.CategoryRepository;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category updateCategory(long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Profile not found"));
        logger.info("profile {}", categoryRequest);
        category.setName(categoryRequest.getName());
        category.setParentId(categoryRequest.getParentId());
        return categoryRepository.save(category);
    }

    public Category create(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setParentId(categoryRequest.getParentId());
        return  categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found"));
        logger.info("category {}", category);
        return category;
    }

    public List<Category> findByParentId(Integer parentId){
        List<Category> categoryList = categoryRepository.findByParentId(parentId);
        logger.info("category {}", categoryList);
        return categoryList;
    }
}
