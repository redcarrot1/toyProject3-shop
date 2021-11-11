package com.example.memberservice.service;

import com.example.memberservice.entity.Member;
import com.example.memberservice.form.RequestLogin;
import com.example.memberservice.form.RequestSignup;
import com.example.memberservice.form.ResponseLogin;
import com.example.memberservice.form.ResponseSignup;
import org.springframework.http.ResponseEntity;

public interface MemberService{
    Member memberSignup(RequestSignup form);
    ResponseEntity<ResponseLogin> login(RequestLogin form);
}
