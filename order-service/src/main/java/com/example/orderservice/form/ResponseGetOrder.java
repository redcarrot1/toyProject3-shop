package com.example.orderservice.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ResponseGetOrder {
    Long orderId;
    Integer orderStatus;
    Date orderDate;
}
