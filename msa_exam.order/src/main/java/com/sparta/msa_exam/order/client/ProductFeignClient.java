package com.sparta.msa_exam.order.client;

import com.sparta.msa_exam.order.dto.ProductClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @GetMapping("/products")
    List<ProductClientResponseDto> getProducts();
}
