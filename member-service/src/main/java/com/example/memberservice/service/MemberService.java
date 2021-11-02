package com.example.memberservice.service;

import com.example.memberservice.form.RequestLogin;
import com.example.memberservice.form.RequestSignup;
import com.example.memberservice.form.ResponseSignup;

public interface MemberService{
    ResponseSignup memberSignup(RequestSignup form);
    String login(RequestLogin form);
}
