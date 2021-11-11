package com.example.cartservice.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderInCart {
    private Long memberId;
    List<RequestOrderItemInCart> orderItems = new ArrayList<>();
}
