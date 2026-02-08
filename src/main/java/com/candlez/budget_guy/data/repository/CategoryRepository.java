package com.candlez.budget_guy.data.repository;

import com.candlez.budget_guy.data.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    List<Category> findAllByUserId(UUID userID);
}
