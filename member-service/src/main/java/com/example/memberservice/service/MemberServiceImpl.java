package com.example.memberservice.service;

import com.example.memberservice.entity.Member;
import com.example.memberservice.form.RequestLogin;
import com.example.memberservice.form.RequestSignup;
import com.example.memberservice.form.ResponseSignup;
import com.example.memberservice.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final Environment env;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseSignup memberSignup(RequestSignup form) {
        ModelMapper mapper = new ModelMapper();
        Member member=mapper.map(form, Member.class);
        member.setRole("basic");
        member.setEncryptedPwd(passwordEncoder.encode(form.getPassword()));
        System.out.println(member);
        Member saveMember = memberRepository.save(member);

        ResponseSignup result = new ResponseSignup(saveMember.getMemberId(), saveMember.getName());
        return result;
    }


    @Override
    public String login(RequestLogin form) {
        Member findMember = memberRepository.findByEmail(form.getEmail());
        boolean matches = passwordEncoder.matches(form.getPassword(), findMember.getEncryptedPwd());
        if(!matches){
            log.warn("패스워드가 맞지 않음");
            return null;
        }

        //email, token.secret, expTime 을 넣어서 토큰 제작
        String token = Jwts.builder()
                .setSubject(form.getEmail())
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();
        return token;
    }
}
