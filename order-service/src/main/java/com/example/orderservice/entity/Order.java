package com.example.orderservice.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    private Long orderId;

    @Column(nullable = false)
    private Long memberId;
    @Column(nullable = false)
    private Integer orderStatus; //1 주문 완료. 2 주문 취소

    //초기 데이터가 들어갈때 자동으로 시간대를 입력하고, 앞으로 변경하지 않음.
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value="CURRENT_TIMESTAMP") //h2 db에서 현재 시간을 자동으로 넣어주는 함수
    private Date orderDate;

    @OneToMany(mappedBy = "orderId", orphanRemoval = true)
    List<OrderItem> orderItems=new ArrayList<>();

    public Order(Long memberId, Integer orderStatus) {
        this.memberId = memberId;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }
}
