package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.User;
import com.candlez.budget_guy.data.repository.UserRepository;
import com.candlez.budget_guy.util.provider.DateProvider;
import com.candlez.budget_guy.util.provider.UUIDProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UUIDProvider uuidProvider;
    private final DateProvider dateProvider;

    @Autowired
    public UserService(UserRepository userRepository, UUIDProvider uuidProvider, DateProvider dateProvider) {
        this.userRepository = userRepository;
        this.uuidProvider = uuidProvider;
        this.dateProvider = dateProvider;
    }

    public User createUser(String email, String hashedPassword, String firstName, String lastName) {

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(hashedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        user.setCreatedAt(dateProvider.getCurrentTimestamp());
        user.setUserId(uuidProvider.generateUUID());

        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }
}
