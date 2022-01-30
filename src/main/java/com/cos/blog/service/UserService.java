package com.cos.blog.service;

import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.constant.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 서비스
 * 1. 트랜잭션 관리
 * 2. 두개 이상 서비스 create, update,
 *
 * select 만 있을 경우 : @Transactional(readOnly = true)
 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC 를 해준다. *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    //@Autowired   @RequiredArgsConstructor 통해 생성
    
	private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
	
    //회원가입
    public User userJoin(User user){
    	String rawPassword =user.getPassword(); //1234
    	user.setPassword(passwordEncoder.encode(rawPassword));    	
        user.setRole(RoleType.USER);
        User result=userRepository.save(user);      
        return result;
    }

	public User getByUsername(String name) {
		return userRepository.getByUsername(name);				
	}

    //readOnly = true  : 효과적으로 읽기 전용이므로 런타임 시 해당 최적화가 가능
/*
    @Transactional(readOnly = true)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
*/
    
    
    
    
    
    

}
