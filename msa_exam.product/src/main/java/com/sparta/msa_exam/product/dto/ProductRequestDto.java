package com.sparta.msa_exam.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
        @NotNull(message = "상품명을 입력하세요.")
        String name,

        @NotNull(message = "상품 가격을 입력하세요.")
        @JsonProperty("supply_price")
        Integer supplyPrice
) {
}
