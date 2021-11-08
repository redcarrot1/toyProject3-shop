package com.example.cartservice.form;

import lombok.Data;

@Data
public class RequestCart {
    Integer count;
    Long itemId;
    Long memberId;
}
