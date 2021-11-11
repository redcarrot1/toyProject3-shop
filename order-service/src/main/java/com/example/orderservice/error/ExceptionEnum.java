package com.example.orderservice.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    NO_ITEM(HttpStatus.BAD_REQUEST, "O01", "잘못된 ITEM을 주문했습니다."),
    NO_STOCK_ITEM(HttpStatus.BAD_REQUEST, "O02", "재고보다 많은 수량을 주문했습니다."),
    NO_ORDER_BY_ORDERID(HttpStatus.BAD_REQUEST, "O03", "orderId로 조회된 주문이 없습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
    }