package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductFeignClient;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.repository.OrderRepository;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductFeignClient productFeignClient;

    public List<ProductResponseDto> getProducts() {
        return productFeignClient.getProducts();
    }

//    public void createOrder(OrderRequestDto orderRequestDto) {
//        Order order = Order.builder()
//                .name(orderRequestDto.name())
//                .productIds()
//                .build();
//        orderRepository.save(order);
//    }
}
