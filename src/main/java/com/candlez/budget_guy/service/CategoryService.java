package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Category;
import com.candlez.budget_guy.data.repository.CategoryRepository;
import com.candlez.budget_guy.util.provider.DateProvider;
import com.candlez.budget_guy.util.provider.UUIDProvider;
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

    public Category createCategory(String name, String description, UUID userId) {
        Category category = new Category();

        category.setName(name);
        category.setDescription(description);
        category.setUserId(userId);

        category.setCreatedAt(dateProvider.getCurrentTimestamp());
        category.setCategoryId(uuidProvider.generateUUID());

        return this.categoryRepository.save(category);
    }

}
