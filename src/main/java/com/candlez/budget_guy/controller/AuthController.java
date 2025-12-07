package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.data.dto.request.LoginRequestDto;
import com.candlez.budget_guy.data.dto.request.SignupRequestDto;
import com.candlez.budget_guy.data.dto.response.UserResponseDto;
import com.candlez.budget_guy.data.entity.User;
import com.candlez.budget_guy.exception.UnauthorizedException;
import com.candlez.budget_guy.service.AuthService;
import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import com.candlez.budget_guy.util.rest.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto signupRequestDto) {

        UserResponseDto userResponseDto;
        try {
            User user = authService.signup(signupRequestDto);
            userResponseDto = UserResponseDto.fromUser(user);
        } catch (Exception e) {
            return ApiErrorResponse.sendOne(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong unexpectedly");
        }
        return ApiResponse.sendCreated(userResponseDto.getUserId(), userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {

        UserResponseDto userResponseDto;
        try {
            User user = authService.login(loginRequestDto);

            String token = authService.generateToken(user);

            ResponseCookie cookie = authService.createCookie(token);

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            userResponseDto = UserResponseDto.fromUser(user);
        } catch (UnauthorizedException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Something went wrong!", e);
            return ApiErrorResponse.sendOne(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong unexpectedly");
        }
        return ApiResponse.sendOne(userResponseDto.getUserId(), userResponseDto);
    }
}
