package com.example.orderservice.form;

import lombok.Data;

@Data
public class RequestOrderItem {
    private Integer count;
    private Integer orderPrice;
    private Long itemId;
}
