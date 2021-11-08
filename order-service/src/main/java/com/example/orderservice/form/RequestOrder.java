package com.example.orderservice.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestOrder {
    private Long memberId;
    List<RequestOrderItem> orderItems = new ArrayList<>();
}
