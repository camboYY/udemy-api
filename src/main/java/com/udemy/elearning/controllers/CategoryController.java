package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CategoryRequest;
import com.udemy.elearning.mapper.CategoryResponse;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.repository.CategoryRepository;
import com.udemy.elearning.services.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }



    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest categoryRequest) throws BadRequestException {
        Category categoryCreate = categoryService.create(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(categoryCreate);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping()
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    public List<Category> getById(@PathVariable(value = "id") Integer id) {
        return Collections.singletonList(categoryRepository.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
                                                   @Valid @RequestBody Category categoryDetails) throws BadRequestException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BadRequestException("Category not found for this id :: " + categoryId));

        category.setName(categoryDetails.getName());
        category.setParentId(categoryDetails.getParentId());
        final Category updatedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/getByParentId/{parentId}")
    public List<Category> getAllChild(@PathVariable(value = "parentId") Integer parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    @GetMapping("/getCategoryParent/{id}")
    public List<Category> getCategoryParent(@PathVariable(value = "id") Integer category_id) {
        Category category_child = categoryRepository.findById(category_id);
        Integer parent_category_id = category_child.getParentId();
        return Collections.singletonList(categoryRepository.findById(parent_category_id));
    }


}
