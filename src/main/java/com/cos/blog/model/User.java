package com.cos.blog.model;

import com.cos.blog.constant.Role;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity //User 클래스가 MySQL 에 테이블이 생성이 된다.
public class User {

    @Id//Primary key
    //IDENTITY : 프로젝트에 연결된  DB의 넘버링 전략을 따라간다. , 기본키 생성을 데이터베이스에 위임 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username;  //아이디

    @Column(nullable = false, length = 100) //123456 => 해쉬 (비밀번호 암호화)
    private String password; // 비밀번호

    @Column(nullable = false, length = 50)
    private String email; //이메일

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'user'")
    private Role role;// Enum을 쓰는게 좋다.

    @CreationTimestamp //시간이 자동 입력
    private LocalDateTime createDate; //등록일

    //private Timestamp createDate;


}
