package com.example.memberservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "member")
public class Member {
    @Id @GeneratedValue
    private Long memberId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String encryptedPwd;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String role;

    //초기 데이터가 들어갈때 자동으로 시간대를 입력하고, 앞으로 변경하지 않음.
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value="CURRENT_TIMESTAMP") //h2 db에서 현재 시간을 자동으로 넣어주는 함수
    private Date signupAt;

    public void setRole(String role) {
        this.role = role;
    }

    public void setEncryptedPwd(String password) {
        this.encryptedPwd = password;
    }
}
