package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.data.dto.request.CreateCategoryRequestDto;
import com.candlez.budget_guy.data.dto.response.CategoryResponseDto;
import com.candlez.budget_guy.data.entity.Category;
import com.candlez.budget_guy.data.mapper.CategoryMapper;
import com.candlez.budget_guy.service.CategoryService;
import com.candlez.budget_guy.util.rest.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        UUID userID = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Category> categories = categoryService.getCategories(userID);

        List<CategoryResponseDto> categoryResponseDtos = categories.stream().map(categoryMapper::toResponseDto).toList();

        return ApiResponse.sendList(categoryResponseDtos);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequestDto categoryDto) {
        UUID userID = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category category = categoryService.createCategory(categoryDto.getName(), categoryDto.getDescription(), userID);

        CategoryResponseDto categoryResponseDto = this.categoryMapper.toResponseDto(category);

        return ApiResponse.sendCreated(category.getCategoryID(), categoryResponseDto);
    }
}
