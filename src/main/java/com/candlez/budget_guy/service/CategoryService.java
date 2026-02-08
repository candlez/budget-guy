package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Category;
import com.candlez.budget_guy.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories(UUID userID) {
        return this.categoryRepository.findAllByUserId(userID);
    }

    public Category createCategory(String name, String description, UUID userId) {
        Category category = new Category();

        category.setName(name);
        category.setDescription(description);
        category.setUserId(userId);

        category.setCreatedAt(Instant.now());
        category.setCategoryId(UUID.randomUUID());

        return this.categoryRepository.save(category);
    }

}
