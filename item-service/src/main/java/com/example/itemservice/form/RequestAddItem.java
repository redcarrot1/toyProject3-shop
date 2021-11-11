package com.example.itemservice.form;

import lombok.Data;

@Data
public class RequestAddItem {
    private String itemDetail;
    private String itemName;
    private Integer price;
    private Integer stock;
}