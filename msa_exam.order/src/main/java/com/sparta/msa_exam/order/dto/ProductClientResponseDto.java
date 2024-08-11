package com.sparta.msa_exam.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProductClientResponseDto(
        @JsonProperty("product_id")
        Long productId,

        String name,

        @JsonProperty("supply_price")
        Integer supplyPrice
) {
}
