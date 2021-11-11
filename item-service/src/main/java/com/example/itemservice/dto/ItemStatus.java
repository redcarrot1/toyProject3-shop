package com.example.itemservice.dto;

public enum ItemStatus {
    SELLING_ITEM(1),
    SOLD_OUT_ITEM(2),
    DELETE_ITEM(3),

    ;

    private final int value;

    ItemStatus(int value) { this.value = value; }

    public int getValue() { return value; }
}
