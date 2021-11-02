package com.example.memberservice.form;

import lombok.Data;

@Data
public class RequestLogin {
    private String email;
    private String password;
}
