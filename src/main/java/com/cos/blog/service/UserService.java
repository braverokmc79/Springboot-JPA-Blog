package com.cos.blog.service;

import java.sql.SQLException;

import javax.persistence.EntityExistsException;

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

	public void updateUser(User requestUser) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		//select 를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update 문 잘려준다.		
		User persistanceUser =userRepository.findById(requestUser.getId()).orElseThrow(EntityExistsException::new);
		//User persistanceUser =userRepository.getByUsername(requestUser.getUsername());
		//getByUsername 를 해도 업데이트 처리 된다. 즉 User 객체만 변경되면 업데이트 처리된다.
		
		String rawPassword=requestUser.getPassword();
	    String encPassword=passwordEncoder.encode(rawPassword);
	    persistanceUser.setPassword(encPassword);
	    persistanceUser.setEmail(requestUser.getEmail());
		//회원 수정 함수 종료시 ==서비스 종료 == 트랜잭션 종료 = commit 이 자동으로 된다.
	    //영속화된 pseristance 객체의 변화가 감지되면 더티체킹이 되어 updatge 문을 날려줌.
	}
	
	

    //readOnly = true  : 효과적으로 읽기 전용이므로 런타임 시 해당 최적화가 가능
/*
    @Transactional(readOnly = true)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
*/
    
    
    
    
    
    

}
