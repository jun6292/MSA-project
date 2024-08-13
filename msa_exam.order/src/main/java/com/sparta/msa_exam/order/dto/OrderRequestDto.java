package com.sparta.msa_exam.order.dto;

import lombok.Builder;

@Builder
public record OrderRequestDto(
        String name
) {
}
