package com.example.orderservice.service;

import com.example.orderservice.client.ItemServiceClient;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.form.RequestOrder;
import com.example.orderservice.form.ResponseCancelOrder;
import com.example.orderservice.form.ResponseGetOrder;
import com.example.orderservice.form.ResponseOrder;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemServiceClient itemServiceClient;

    public ResponseOrder save(RequestOrder form) {
        Order order = new Order(form.getMemberId(), 1);
        orderRepository.save(order);

        form.getOrderItems().forEach(e->{
            OrderItem save = orderItemRepository.save(
                    new OrderItem(e.getCount(), e.getOrderPrice(), e.getItemId(), order));
            order.getOrderItems().add(save);

            //TODO 재고가 없으면 오류처리

            itemServiceClient.reduceItemStock(e.getItemId(), e.getCount());
        });
        return new ResponseOrder(order.getOrderId(), order.getOrderStatus());
    }


    public List<ResponseGetOrder> findByMemberId(Long memberId) {
        List<Order> findOrder = orderRepository.findByMemberId(memberId);
        List<ResponseGetOrder> result=new ArrayList<>();

        findOrder.forEach(e->
                result.add(new ResponseGetOrder(e.getOrderId(), e.getOrderStatus(), e.getOrderDate()))
        );
        return result;
    }

    public ResponseCancelOrder CancelByOrderId(Long orderId) {
        orderRepository.deleteById(orderId);
        return new ResponseCancelOrder(true);
    }
}
