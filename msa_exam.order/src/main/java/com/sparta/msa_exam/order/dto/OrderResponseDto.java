package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.product.dto.ProductResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponseDto(
        Long id,
        String name,
        List<ProductResponseDto> products
) {
}
