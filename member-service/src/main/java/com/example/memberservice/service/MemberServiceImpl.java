package com.example.memberservice.service;

import com.example.memberservice.entity.Member;
import com.example.memberservice.error.ApiException;
import com.example.memberservice.error.ExceptionEnum;
import com.example.memberservice.form.RequestLogin;
import com.example.memberservice.form.RequestSignup;
import com.example.memberservice.form.ResponseLogin;
import com.example.memberservice.form.ResponseSignup;
import com.example.memberservice.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final Environment env;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Member memberSignup(RequestSignup form) {
        if (memberRepository.findByEmail(form.getEmail()).isPresent())
            throw new ApiException(ExceptionEnum.DUPLICATE_EMAIL);

        ModelMapper mapper = new ModelMapper();
        Member member = mapper.map(form, Member.class);
        member.setRoleAndEncryptedPwd("basic", passwordEncoder.encode(form.getPassword()));

        return memberRepository.save(member);
    }


    @Override
    public ResponseEntity<ResponseLogin> login(RequestLogin form) {
        Member findMember = memberRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new ApiException(ExceptionEnum.NO_MATCH_EMAIL));
        boolean match = passwordEncoder.matches(form.getPassword(), findMember.getEncryptedPwd());
        if (!match) throw new ApiException(ExceptionEnum.NO_MATCH_PASSWORD);

        String token = tokenBuilder(findMember.getEmail());

        HttpHeaders header = new HttpHeaders();
        header.add("token", token);

        return ResponseEntity.ok().headers(header)
                .body(new ResponseLogin(findMember.getName()));
    }

    public String tokenBuilder(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();
    }
}
