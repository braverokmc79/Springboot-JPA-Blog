package com.cos.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //User 클래스가 MySQL 에 테이블이 생성이 된다.
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본키 생성을 데이터베이스에 위임 한다. auto_increment
    private Long id;

    @Column(nullable = false, length =100)
    private String title;

    //섬머노트 라이브러리 <html> 태그가 섞여서 디자인됨.
    @Lob //대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count;//조회수


    @ManyToOne //Many=Many, User=One
    @JoinColumn(name="userId")
    private User user; //DB 는 오브젝트를 저장활 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.1


    @CreationTimestamp
    private LocalDateTime createDate; //등록일






}





