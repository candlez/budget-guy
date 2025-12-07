package com.candlez.budget_guy.data.repository;

import com.candlez.budget_guy.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
