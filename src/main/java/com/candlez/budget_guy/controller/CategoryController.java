package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.data.dto.request.InputCategoryRequestDto;
import com.candlez.budget_guy.data.dto.response.CategoryResponseDto;
import com.candlez.budget_guy.data.entity.Category;
import com.candlez.budget_guy.data.mapper.CategoryMapper;
import com.candlez.budget_guy.service.CategoryService;
import com.candlez.budget_guy.util.rest.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<?> getCategories(@AuthenticationPrincipal UUID userId) {

        List<Category> categories = categoryService.getCategories(userId);

        List<CategoryResponseDto> categoryResponseDtos = categories.stream().map(categoryMapper::toResponseDto).toList();

        return ApiResponse.sendList(categoryResponseDtos);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @AuthenticationPrincipal UUID userId,
            @RequestBody @Valid InputCategoryRequestDto categoryDto
    ) {

        Category category = categoryService.createCategory(categoryDto.getName(), categoryDto.getDescription(), userId);

        CategoryResponseDto categoryResponseDto = this.categoryMapper.toResponseDto(category);

        return ApiResponse.sendCreated(category.getCategoryId(), categoryResponseDto);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable UUID categoryId, @AuthenticationPrincipal UUID userId) {

        Category category = categoryService.getCategory(userId, categoryId);

        CategoryResponseDto categoryResponseDto = this.categoryMapper.toResponseDto(category);

        return ApiResponse.sendOne(categoryResponseDto.getCategoryId(), categoryResponseDto);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<?> replaceCategory(
            @PathVariable UUID categoryId,
            @AuthenticationPrincipal UUID userId,
            @RequestBody @Valid InputCategoryRequestDto categoryDto
    ) {

        Category category = this.categoryService.updateCategory(
                userId,
                categoryId,
                categoryDto.getName(),
                categoryDto.getDescription()
        );

        CategoryResponseDto categoryResponseDto = this.categoryMapper.toResponseDto(category);

        return ApiResponse.sendOne(categoryResponseDto.getCategoryId(), categoryResponseDto);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable UUID categoryId,
            @AuthenticationPrincipal UUID userId
    ) {

        this.categoryService.deleteCategory(userId, categoryId);

        return ApiResponse.sendDeleted();
    }
}
