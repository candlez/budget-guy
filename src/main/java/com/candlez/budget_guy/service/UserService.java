package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.User;
import com.candlez.budget_guy.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String hashedPassword, String firstName, String lastName) {

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(hashedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        user.setCreatedAt(Instant.now());
        user.setUserId(UUID.randomUUID());

        return userRepository.save(user);
    }
}
