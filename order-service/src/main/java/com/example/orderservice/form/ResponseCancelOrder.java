package com.example.orderservice.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCancelOrder {
    boolean status; //0 성공, 1 실패
}
