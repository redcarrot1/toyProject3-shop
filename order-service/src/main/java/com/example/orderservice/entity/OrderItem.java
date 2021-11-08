package com.example.orderservice.entity;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long orderItemId;

    private Integer count;
    private Integer orderPrice;
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order orderId;

    public OrderItem(Integer count, Integer orderPrice, Long itemId, Order orderId) {
        this.count = count;
        this.orderPrice = orderPrice;
        this.itemId = itemId;
        this.orderId = orderId;
    }

    public OrderItem() {

    }
}
