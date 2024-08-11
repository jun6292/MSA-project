package com.sparta.msa_exam.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record JwtTokenResponseDto(
        @NotBlank String accessToken
) {
}
