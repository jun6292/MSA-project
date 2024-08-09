package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.service.ProductService;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    /**
     * 상품 등록 API
     * @return
     */
    @PostMapping("")
    public String createProduct() {
        return "상품이 등록되었습니다.";
    }

    /**
        * 상품 목록 조회 API
     * @return 상품 목록
     */
    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> readProducts() {
        return ResponseEntity.ok(productService.readProducts());
    }
}
