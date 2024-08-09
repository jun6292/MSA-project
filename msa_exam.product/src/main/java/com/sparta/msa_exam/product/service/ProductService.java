package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.repository.ProductRepository;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDto> readProducts() {
        return List.of(
                new ProductResponseDto(1L, "맥북", 2000000),
                new ProductResponseDto(2L, "아이패드", 1000000)
        );
    }
}
