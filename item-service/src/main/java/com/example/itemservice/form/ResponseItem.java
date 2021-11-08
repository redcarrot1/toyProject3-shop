package com.example.itemservice.form;

import lombok.Data;

@Data
public class ResponseItem {
    private String itemName;
    private Integer itemSellStatus; //1 정상 판매 중. 2 품절. 3 삭제
    private Integer price;
    private Integer stock;
}
