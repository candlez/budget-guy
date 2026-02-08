package com.candlez.budget_guy.data.mapper;

import com.candlez.budget_guy.data.dto.response.CategoryResponseDto;
import com.candlez.budget_guy.data.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDto toResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryID(category.getCategoryID());
        categoryResponseDto.setUserID(category.getUserID());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());
        categoryResponseDto.setCreatedAt(category.getCreatedAt());
        return categoryResponseDto;
    }
}
