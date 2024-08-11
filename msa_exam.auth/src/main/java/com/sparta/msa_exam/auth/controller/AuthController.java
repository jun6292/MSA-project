package com.sparta.msa_exam.auth.controller;

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
    public ResponseEntity<?> createAuthToken(@RequestParam(name = "user_id") String userId) {
        return ResponseEntity.ok(AuthService.createAccessToken(userId));
    }

    @PostMapping("/signUp")
    public String signUp() {
        return "signUp";
    }
}
