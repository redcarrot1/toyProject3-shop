package com.example.itemservice.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    NO_ITEM(HttpStatus.BAD_REQUEST, "I01", "해당 ID에 맞는 ITEM이 없습니다."),
    NO_STOCK(HttpStatus.BAD_REQUEST, "I02", "요청 수량보다 재고 수량이 더 적습니다."),
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