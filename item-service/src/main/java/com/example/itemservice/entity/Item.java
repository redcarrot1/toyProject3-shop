package com.example.itemservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue
    private Long itemId;

    private String itemDetail;
    private String itemName;
    private Integer itemSellStatus; //1 정상 판매 중. 2 품절. 3 삭제
    private Integer price;
    private Integer stock;
}
