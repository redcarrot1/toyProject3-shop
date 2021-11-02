package com.example.memberservice.controller;

import com.example.memberservice.form.RequestLogin;
import com.example.memberservice.form.RequestSignup;
import com.example.memberservice.form.ResponseLogin;
import com.example.memberservice.form.ResponseSignup;
import com.example.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService service;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Member Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", with token secret=" + env.getProperty("token.secret")
                + ", with token time=" + env.getProperty("token.expiration_time"));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseSignup> memberSignup(@RequestBody RequestSignup form){
        ResponseSignup result = service.memberSignup(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> memberLogin(@RequestBody RequestLogin form) {
        String token = service.login(form);
        if(token==null) {
            //TODO 로그인 실패
            log.warn("로그인 실패 email={}", form.getEmail());
        }
        HttpHeaders header = new HttpHeaders();
        header.add("token", token);
        return ResponseEntity.ok().headers(header)
                .body(new ResponseLogin("admin"));
    }
}
