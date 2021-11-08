package com.example.orderservice.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseOrder {
    Long orderId;
    Integer orderStatus;
}
