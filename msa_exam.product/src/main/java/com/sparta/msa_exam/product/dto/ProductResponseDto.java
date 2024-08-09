package com.sparta.msa_exam.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProductResponseDto(
        @JsonProperty("product_id")
        Long productId,

        String name,

        @JsonProperty("supply_price")
        Integer supplyPrice
) {
}
