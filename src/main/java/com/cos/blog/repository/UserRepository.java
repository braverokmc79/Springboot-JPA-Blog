package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DAO
//자동으로 bean 등록이 된다.
//유저 테이블의 primary key 는 Long
//<User, Long>
//@Repository 생략이 가능하다.

public interface UserRepository extends JpaRepository<User, Long> {
	
	//SELECT * FROM user WHERE username =1?;
	Optional<User> findByUsername(String username);
	
	
	User findByEmail(String email);

	
}


// JPA Naming 전략
// SELECT * FROM user WHERE username=?1 AND password=?2
//User findByUsernameAndPassword(String username, String passwrod);

//@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
//User login(String username, String password);
