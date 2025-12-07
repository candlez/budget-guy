package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.dto.request.LoginRequestDto;
import com.candlez.budget_guy.data.dto.request.SignupRequestDto;
import com.candlez.budget_guy.data.entity.User;
import com.candlez.budget_guy.exception.UnauthorizedException;
import com.candlez.budget_guy.security.CookieBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    public static final int THREE_DAYS = 60 * 60 * 24 * 3;

    public static final String JWT_COOKIE_NAME = "budget_guy_token";

    private final PasswordEncoder passwordEncoder;

    private final CookieBuilderFactory cookieFactory;

    private final UserService userService;

    private final JwtService jwtService;

    @Autowired
    public AuthService(
            PasswordEncoder passwordEncoder,
            UserService userService,
            JwtService jwtService,
            CookieBuilderFactory cookieFactory
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtService = jwtService;
        this.cookieFactory = cookieFactory;
    }

    public User signup(SignupRequestDto signupRequestDto) {

        String hashedPassword = passwordEncoder.encode(signupRequestDto.getPassword());
        return userService.createUser(
                signupRequestDto.getEmail(),
                hashedPassword,
                signupRequestDto.getFirstName(),
                signupRequestDto.getLastName()
        );
    }

    public User login(LoginRequestDto loginRequestDto) {
        Optional<User> userOptional = userService.getUserByEmail(loginRequestDto.getEmail());
        if (userOptional.isEmpty()) {
            throw new UnauthorizedException("Invalid username and password");
        }
        User user = userOptional.get();

        boolean passwordMatches = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPasswordHash());
        if (!passwordMatches) {
            throw new UnauthorizedException("Invalid username and password");
        }
        return user;
    }

    public String generateToken(User user) {
        return jwtService.generateToken(user.getUserId().toString());
    }

    public ResponseCookie createCookie(String token) {
        return cookieFactory.getBuilder().value(token).build();
    }

}
