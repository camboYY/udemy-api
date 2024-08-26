package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CategoryRequest;
import com.udemy.elearning.mapper.CategoryResponse;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.services.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest categoryRequest) throws BadRequestException {
        Category categoryCreate = categoryService.create(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(categoryCreate);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categoryList = categoryService.findAll();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable(value = "id") Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.updateCategory(id,categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(category);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping("/getByParentId/{parentId}")
    public ResponseEntity<List<Category>> getAllChild(@PathVariable(value = "parentId") Integer parentId) {
        List<Category> categoryList = categoryService.findByParentId(parentId);
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/getCategoryParent/{id}")
    public ResponseEntity<Category> getCategoryParent(@PathVariable(value = "id") Long category_id) {
        Category category_child = categoryService.findById(category_id);
        Integer parent_category_id = category_child.getParentId();
        Category categoryList = categoryService.findById(Long.valueOf(parent_category_id));
        return ResponseEntity.ok(categoryList);
    }

}
