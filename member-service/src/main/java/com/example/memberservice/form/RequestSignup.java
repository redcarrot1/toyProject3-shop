package com.example.memberservice.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RequestSignup {
    @NotBlank @Email
    private String email;
    @NotBlank @Length(min=6)
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
