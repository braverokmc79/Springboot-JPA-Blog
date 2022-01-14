package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO
//자동으로 bean 등록이 된다.
//유저 테이블의 primary key 는 Long
//<User, Long>
//@Repository 생략이 가능하다.

public interface UserRepository extends JpaRepository<User, Long> {

}
