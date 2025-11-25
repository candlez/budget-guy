package com.candlez.budget_guy.data.repository;

import com.candlez.budget_guy.data.entity.Statement;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StatementRepository extends CrudRepository<Statement, UUID> {
}
