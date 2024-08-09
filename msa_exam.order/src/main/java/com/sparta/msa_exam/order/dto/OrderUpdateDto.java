package com.sparta.msa_exam.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateDto(
        @JsonProperty("product_id")
        @NotNull(message = "상품을 선택해주세요.")
        Long productId
) {
}
