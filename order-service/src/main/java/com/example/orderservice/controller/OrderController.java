package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.form.RequestOrder;
import com.example.orderservice.form.ResponseCancelOrder;
import com.example.orderservice.form.ResponseGetOrder;
import com.example.orderservice.form.ResponseOrder;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseOrder order(@RequestBody RequestOrder form) {
        Order saveOrder = orderService.save(form);
        return new ResponseOrder(saveOrder.getOrderId(), saveOrder.getOrderStatus());
    }

    @GetMapping("/orders/{memberId}")
    public List<ResponseGetOrder> getOrders(@PathVariable Long memberId) {
        List<Order> findOrders = orderService.findByMemberId(memberId);
        List<ResponseGetOrder> result = new ArrayList<>();

        findOrders.forEach(e -> result.add(new ResponseGetOrder(e.getOrderId(), e.getOrderStatus(), e.getOrderDate())));
        return result;
    }

    @GetMapping("/{orderId}/cancel")
    public ResponseCancelOrder cancelOrder(@PathVariable Long orderId) {
        boolean result = orderService.CancelByOrderId(orderId);
        return new ResponseCancelOrder(result);
    }
}
