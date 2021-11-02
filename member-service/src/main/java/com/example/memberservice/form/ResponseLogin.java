package com.example.memberservice.form;

import lombok.Data;

@Data
public class ResponseLogin {
    private String name;

    public ResponseLogin(String name) {
        this.name = name;
    }
}
