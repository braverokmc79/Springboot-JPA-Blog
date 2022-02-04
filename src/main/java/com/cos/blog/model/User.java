package com.cos.blog.model;

import com.cos.blog.constant.RoleType;

import ch.qos.logback.classic.db.names.ColumnName;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
@Entity //User 클래스가 MySQL 에 테이블이 생성이 된다.
//@DynamicInsert //insert 시에 널인 필드를 제외 시켜 준다.디폴트값을 선언한 것은 null 값이 들어와도 자동으로 등록처리해 준다.
public class User {

    @Id//Primary key
    //IDENTITY : 프로젝트에 연결된  DB의 넘버링 전략을 따라간다. , 기본키 생성을 데이터베이스에 위임 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // 시퀀스, auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username;  //아이디

    @Column(nullable = false, length = 100) //123456 => 해쉬 (비밀번호 암호화)
    private String password; // 비밀번호

    //@Column(nullable = false, length = 50, unique = true)
    @Column(nullable = false, length = 50)
    private String email; //이메일


    //@ColumnDefault("'USER'")
    //DB는 RoleType 이라는 것이 없다. 따라서 Enum 이 String 이라는 것을 알려준다.
    @Enumerated(EnumType.STRING)
    private RoleType role;// Enum을 쓰는게 좋다.

    
    private String oauth; //kakao, google
    
    
    @CreationTimestamp //시간이 자동 입력
    private LocalDateTime createDate; //등록일

    //private Timestamp createDate;




}
