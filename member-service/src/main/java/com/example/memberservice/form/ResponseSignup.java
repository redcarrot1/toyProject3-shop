package com.example.memberservice.form;

import lombok.Data;


@Data
public class ResponseSignup {
    private Long memberId;
    private String name;

    public ResponseSignup(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
