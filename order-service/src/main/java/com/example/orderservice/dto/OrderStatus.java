package com.example.orderservice.dto;

public enum OrderStatus {
    ORDER_COMPLETE(1),
    ORDER_CANCEL(2),

    ;

    private final int value;

    OrderStatus(int value) { this.value = value; }

    public int getValue() { return value; }
}
