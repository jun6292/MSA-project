package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.dto.ProductClientResponseDto;

import java.util.List;

public interface ProductClientInterface {
    List<ProductClientResponseDto> getProducts();
}
