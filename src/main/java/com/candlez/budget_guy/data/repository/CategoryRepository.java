package com.candlez.budget_guy.data.repository;

import com.candlez.budget_guy.data.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    List<Category> findAllByUserId(UUID userID);

    Optional<Category> findByUserIdAndCategoryId(UUID userId, UUID categoryId);

    void deleteByUserIdAndCategoryId(UUID userId, UUID categoryId);
}
