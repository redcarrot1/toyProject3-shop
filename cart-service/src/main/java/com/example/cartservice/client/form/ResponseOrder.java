package com.example.cartservice.client.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {
    Long orderId;
    Integer orderStatus;
}
