package com.example.cartservice.client.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private Long memberId;
    List<RequestOrderItem> orderItems;
}
