package com.sparta.msa_exam.auth.dto;

import jakarta.validation.constraints.NotEmpty;

public record SignInRequestDto(
        @NotEmpty String password
) {
}
