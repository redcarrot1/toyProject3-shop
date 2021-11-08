package com.example.orderservice.controller;

import com.example.orderservice.form.RequestOrder;
import com.example.orderservice.form.ResponseCancelOrder;
import com.example.orderservice.form.ResponseGetOrder;
import com.example.orderservice.form.ResponseOrder;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    public ResponseOrder order(@RequestBody RequestOrder form) {
        ResponseOrder result = orderService.save(form);
        return result;
    }

    @GetMapping("/orders/{memberId}")
    public List<ResponseGetOrder> getOrders(@PathVariable Long memberId) {
        return orderService.findByMemberId(memberId);
    }

    @GetMapping("/{orderId}/cancel")
    public ResponseCancelOrder cancelOrder(@PathVariable Long orderId) {
        return orderService.CancelByOrderId(orderId);
    }
}
