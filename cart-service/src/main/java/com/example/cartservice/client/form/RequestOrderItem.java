package com.example.cartservice.client.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderItem {
    private Integer count;
    private Integer orderPrice;
    private Long itemId;
}
