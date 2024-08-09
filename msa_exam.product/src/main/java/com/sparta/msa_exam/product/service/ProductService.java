package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponseDto> readProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = productList.stream()
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getId())
                        .name(product.getName())
                        .supplyPrice(product.getSupplyPrice())
                        .build())
                .collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Transactional
    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.name())
                .supplyPrice(productRequestDto.supplyPrice())
                .build();
        productRepository.save(product);
    }
}
