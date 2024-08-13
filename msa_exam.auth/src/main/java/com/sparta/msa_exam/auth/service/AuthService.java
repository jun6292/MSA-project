package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.common.exception.CommonException;
import com.sparta.msa_exam.auth.common.response.ErrorCode;
import com.sparta.msa_exam.auth.dto.JwtTokenResponseDto;
import com.sparta.msa_exam.auth.dto.SignInRequestDto;
import com.sparta.msa_exam.auth.dto.SignUpRequestDto;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.repository.UserRepository;
import com.sparta.msa_exam.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public JwtTokenResponseDto signIn(String username, SignInRequestDto signInRequestDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(signInRequestDto.password(), user.getPassword())) {
            throw new CommonException(ErrorCode.UNAUTHORIZED);
        }

        return jwtUtil.createAuthToken(user.getUsername(), user.getRole());
    }


    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByUsername(signUpRequestDto.username())) {
            throw new CommonException(ErrorCode.DUPLICATED_USER);
        }

        User user = User.builder()
                .username(signUpRequestDto.username())
                .password(passwordEncoder.encode(signUpRequestDto.password()))
                .role("USER")
                .build();

        userRepository.save(user);
    }

    // 회원 존재 여부 검증
    public Boolean verifyUser(final String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
