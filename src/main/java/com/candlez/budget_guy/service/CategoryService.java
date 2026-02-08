package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Category;
import com.candlez.budget_guy.data.repository.CategoryRepository;
import com.candlez.budget_guy.util.provider.UUIDProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final UUIDProvider uuidProvider;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UUIDProvider uuidProvider) {
        this.categoryRepository = categoryRepository;
        this.uuidProvider = uuidProvider;
    }

    public List<Category> getCategories(UUID userId) {
        return this.categoryRepository.findAllByUserId(userId);
    }

    public Category createCategory(String name, String description, UUID userId) {
        Category category = new Category();

        category.setName(name);
        category.setDescription(description);
        category.setUserId(userId);

        category.setCreatedAt(Instant.now());
        category.setCategoryId(uuidProvider.generateUUID());

        return this.categoryRepository.save(category);
    }

}
