package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.dto.request.CreateCategoryRequestDto;
import com.candlez.budget_guy.data.entity.Category;
import com.candlez.budget_guy.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories(UUID userID) {
        return this.categoryRepository.findAllByUserID(userID);
    }

    public Category createCategory(String name, String description, UUID userID) {
        Category category = new Category();

        category.setName(name);
        category.setDescription(description);
        category.setUserID(userID);

        category.setCreatedAt(Instant.now());
        category.setCategoryID(UUID.randomUUID());

        return this.categoryRepository.save(category);
    }

}
