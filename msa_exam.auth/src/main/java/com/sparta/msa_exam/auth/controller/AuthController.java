package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.common.response.CommonResponse;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/signIn")
    public CommonResponse<?> createAuthToken(@RequestParam(name = "user_id") String userId) {
        return CommonResponse.ok(authService.createAuthToken(userId));
    }

//    @PostMapping("/signUp")
//    public CommonResponse<?> signUp() {
//        return "signUp";
//    }
}
