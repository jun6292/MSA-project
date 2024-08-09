package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductFeignClient;
import com.sparta.msa_exam.order.common.exception.CommonException;
import com.sparta.msa_exam.order.common.response.ErrorCode;
import com.sparta.msa_exam.order.dto.OrderUpdateDto;
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

    public void updateOrder(Long orderId, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CommonException(ErrorCode.ORDER_NOT_FOUND));
        List<ProductResponseDto> products = getProducts();
        if (isProductExist(
                orderUpdateDto.productId(),
                products.stream().map(p -> p.productId()).toList())
        ) {
            order.updateOrder(orderUpdateDto.productId());
            orderRepository.save(order);
        } else {
            throw new CommonException(ErrorCode.CANNOT_FIND_PRODUCT);
        }
    }

    public boolean isProductExist(Long productId, List<Long> existProductIds) {
        if (existProductIds.contains(productId)) {
            return true;
        }
        return false;
    }

//    public void createOrder(OrderRequestDto orderRequestDto) {
//        Order order = Order.builder()
//                .name(orderRequestDto.name())
//                .productIds()
//                .build();
//        orderRepository.save(order);
//    }
}
