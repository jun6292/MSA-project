package com.sparta.msa_exam.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponseDto(
        @JsonProperty("order_id")
        Long orderId,

        @JsonProperty("product_ids")
        List<Long> productIds
) {
}
