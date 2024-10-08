package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.common.response.CommonResponse;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.OrderUpdateDto;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public CommonResponse<?> createOrder(
            @RequestBody OrderRequestDto orderRequestDto
    ) {
        orderService.createOrder(orderRequestDto);
        return CommonResponse.created("주문 추가 완료");
    }

    @GetMapping("/{orderId}")
    public CommonResponse<OrderResponseDto> readOrder(@PathVariable Long orderId) {
        return CommonResponse.ok(orderService.readOrder(orderId));
    }

    @PutMapping("/{orderId}")
    public CommonResponse<?> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrderUpdateDto orderUpdateDto
    ) {
        orderService.updateOrder(orderId, orderUpdateDto);
        return CommonResponse.ok("주문 수정 완료");
    }

}
