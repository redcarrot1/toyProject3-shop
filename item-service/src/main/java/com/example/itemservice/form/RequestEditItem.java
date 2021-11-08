package com.example.itemservice.form;

import lombok.Data;

@Data
public class RequestEditItem {
    private String itemDetail;
    private String itemName;
    private Integer price;
    private Integer stock;
}