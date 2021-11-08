package com.example.cartservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Cart {
    @Id @GeneratedValue
    private Long cartId;

    private Integer count;
    private Long itemId;
    private Long memberId;

    public Cart(Integer count, Long itemId, Long memberId) {
        this.count = count;
        this.itemId = itemId;
        this.memberId = memberId;
    }
}
