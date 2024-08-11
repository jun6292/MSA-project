package com.sparta.msa_exam.auth.dto;

import lombok.Builder;

@Builder
public record SignUpRequestDto(
        String username,
        String password
) {
}
