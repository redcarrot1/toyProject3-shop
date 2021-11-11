package com.example.memberservice.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    NO_MATCH_EMAIL(HttpStatus.BAD_REQUEST, "M01", "해당 이메일에 맞는 회원이 존재하지 않습니다."),
    NO_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "M02", "로그인 패스워드가 맞지 않습니다."),

    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "M03", "회원 이메일이 중복되었습니다."),
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