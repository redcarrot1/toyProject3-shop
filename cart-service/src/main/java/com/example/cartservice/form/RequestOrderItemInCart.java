package com.example.cartservice.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderItemInCart {
    private Integer count;
    private Integer orderPrice;
    private Long itemId;
    private Long cartId;
}
