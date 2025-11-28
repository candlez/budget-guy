package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.dto.SignupRequestDto;
import com.candlez.budget_guy.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public User signup(SignupRequestDto signupRequestDto) {

        String hashedPassword = passwordEncoder.encode(signupRequestDto.getPassword());
        return userService.createUser(
                signupRequestDto.getEmail(),
                hashedPassword,
                signupRequestDto.getFirstName(),
                signupRequestDto.getLastName()
        );
    }


}
