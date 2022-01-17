package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@Service  //Bean 등록
@Transactional
public class PrincipalDetailService  implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	
	/**스프링이 로그인 요청을 가로챌 때, username, passwrod 변수 2개를 가로채는데
	    password 부분처리는 알아서 함.
		username (email) 이 DB 에 있는지만 확인해주면 됨. return
	*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//이메일경우 예
//		User principal=userRepository.findByEmail(email);		
//	    if(principal==null){
//	            throw new UsernameNotFoundException(email);
//	    }
//		   UserDetails result= org.springframework.security.core.userdetails.User.builder()
//	                .username(principal.getEmail())
//	                .password(principal.getPassword())
//	                .roles(principal.getRole().toString())
//	                .build();
			    
		User principal=userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :" + username);
		});	
	    
	   UserDetails result= org.springframework.security.core.userdetails.User.builder()
	                .username(principal.getUsername())
	                .password(principal.getPassword())
	                .roles(principal.getRole().toString())
	                .build();	 		
		return result;  
	}

	
	
}