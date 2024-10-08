package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.common.response.CommonResponse;
import com.sparta.msa_exam.auth.dto.SignInRequestDto;
import com.sparta.msa_exam.auth.dto.SignUpRequestDto;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;

    @GetMapping("/signIn")
    public CommonResponse<?> signIn(
            @RequestBody SignInRequestDto signInRequestDto,
            @RequestParam("user_id") String username
    ) {
        return CommonResponse.ok(authService.signIn(username, signInRequestDto));
    }

    @PostMapping("/signUp")
    public CommonResponse<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto);
        return CommonResponse.created("회원가입이 완료되었습니다.");
    }

    @GetMapping("/verify")
    public Boolean verifyUser(@RequestParam("user_id") String username) {
        log.info("verifyUser: {}", username);
        return authService.verifyUser(username);
    }
}
