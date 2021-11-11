package com.example.orderservice.service;

import com.example.orderservice.client.ItemServiceClient;
import com.example.orderservice.dto.OrderStatus;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.error.ApiException;
import com.example.orderservice.error.ExceptionEnum;
import com.example.orderservice.form.RequestOrder;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemServiceClient itemServiceClient;

    public Order save(RequestOrder form) {
        //TODO order 을 먼저 저장하면 안되는데..
        Order order = orderRepository.save(new Order(form.getMemberId(), OrderStatus.ORDER_COMPLETE.getValue()));

        //TODO 나중에 있는 아이템의 재고가 부족하다면?
        form.getOrderItems().forEach(e -> {
            itemServiceClient.reduceItemStock(e.getItemId(), e.getCount());
            OrderItem orderItem = orderItemRepository.save(
                    new OrderItem(e.getCount(), e.getOrderPrice(), e.getItemId(), order));
            order.getOrderItems().add(orderItem);
        });

        return order;
    }


    public List<Order> findByMemberId(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }

    public Order findByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiException(ExceptionEnum.NO_ORDER_BY_ORDERID));
    }

    public boolean CancelByOrderId(Long orderId) {
        Order findOrder = findByOrderId(orderId);
        orderRepository.delete(findOrder);
        return true;
    }
}
