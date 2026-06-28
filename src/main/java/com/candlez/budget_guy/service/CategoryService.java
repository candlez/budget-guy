package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Category;
import com.candlez.budget_guy.data.repository.CategoryRepository;
import com.candlez.budget_guy.exception.NotFoundException;
import com.candlez.budget_guy.util.provider.DateProvider;
import com.candlez.budget_guy.util.provider.UUIDProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final UUIDProvider uuidProvider;
    private final DateProvider dateProvider;

    @Autowired
    public CategoryService(
            CategoryRepository categoryRepository,
            UUIDProvider uuidProvider,
            DateProvider dateProvider
    ) {
        this.categoryRepository = categoryRepository;
        this.uuidProvider = uuidProvider;
        this.dateProvider = dateProvider;
    }

    public List<Category> getCategories(UUID userId) {
        return this.categoryRepository.findAllByUserId(userId);
    }

    @Transactional
    public Category createCategory(String name, String description, UUID userId) {
        Category category = new Category();

        category.setName(name);
        category.setDescription(description);
        category.setUserId(userId);

        category.setCreatedAt(dateProvider.getCurrentTimestamp());
        category.setCategoryId(uuidProvider.generateUUID());

        return this.categoryRepository.save(category);
    }

    public Category getCategory(UUID userId, UUID categoryId) {
        return this.categoryRepository
                .findByUserIdAndCategoryId(userId, categoryId)
                .orElseThrow(() -> new NotFoundException("Category '" + categoryId + "' not found"));
    }

    // all writes should be transactional
    @Transactional
    public Category updateCategory(UUID userId, UUID categoryId, String name, String description) {
        Category category = this.getCategory(userId, categoryId);

        category.setName(name);
        category.setDescription(description);

        return this.categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(UUID userId, UUID categoryId) {
        this.categoryRepository.deleteByUserIdAndCategoryId(userId, categoryId);
    }

}
