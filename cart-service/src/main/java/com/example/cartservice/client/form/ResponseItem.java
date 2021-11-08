package com.example.cartservice.client.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItem {
    private String itemName;
    private Integer itemSellStatus; //1 정상 판매 중. 2 품절. 3 삭제
    private Integer price;
    private Integer stock;
}
