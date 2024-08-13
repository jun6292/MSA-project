package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.common.response.CommonResponse;
import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.service.ProductService;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public CommonResponse<?> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        productService.createProduct(productRequestDto);
        return CommonResponse.created("상품 등록 완료");
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> readProducts() {
        return ResponseEntity.ok(productService.readProducts());
    }
}
