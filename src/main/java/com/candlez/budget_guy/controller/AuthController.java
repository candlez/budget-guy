package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.data.dto.request.SignupRequestDto;
import com.candlez.budget_guy.data.dto.response.UserResponseDto;
import com.candlez.budget_guy.data.entity.User;
import com.candlez.budget_guy.service.AuthService;
import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import com.candlez.budget_guy.util.rest.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

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
}
