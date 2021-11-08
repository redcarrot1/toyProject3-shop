package com.example.cartservice.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseGetCart {
    Integer count;
    Long itemId;
}
