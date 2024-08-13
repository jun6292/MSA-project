package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductFeignClient;
import com.sparta.msa_exam.order.common.exception.CommonException;
import com.sparta.msa_exam.order.common.response.ErrorCode;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.OrderUpdateDto;
import com.sparta.msa_exam.order.dto.ProductClientResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductFeignClient productFeignClient;

    @Transactional
    public void createOrder(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .name(orderRequestDto.name())
                .build();
        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "orders", key = "#orderId")
    public OrderResponseDto readOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CommonException(ErrorCode.ORDER_NOT_FOUND));
        List<OrderProduct> orderProductList = order.getProductIds();
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderId(order.getId())
                .productIds(orderProductList.stream().map(p -> p.getProductId()).toList())
                .build();
        return orderResponseDto;
    }

    public List<ProductClientResponseDto> getProducts() {
        return productFeignClient.getProducts();
    }

    @Transactional
    public void updateOrder(Long orderId, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CommonException(ErrorCode.ORDER_NOT_FOUND));
        List<ProductClientResponseDto> products = getProducts();
        if (isProductExist(
                orderUpdateDto.productId(),
                products.stream().map(p -> p.productId()).toList())
        ) {
            order.updateOrder(
                    OrderProduct.builder()
                            .productId(orderUpdateDto.productId())
                            .order(order)
                            .build()
            );
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
}
