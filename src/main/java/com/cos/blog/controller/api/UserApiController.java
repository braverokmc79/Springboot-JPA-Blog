package com.cos.blog.controller.api;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final Logger log=LoggerFactory.getLogger(this.getClass());
 			
			
    private final UserService userService;


	private final PrincipalDetailService principalDetailService;
	
	
    @PostMapping("/auth/joinProc")
    public ResponseEntity<?> save(@RequestBody User user) {
        //실제로 DB에 insert 를 하고 아래에서 리턴
    	log.info("회원 가입");
        try{
        	 userService.userJoin(user);            
        }catch (DataIntegrityViolationException e) {
        	return new ResponseEntity<String>("중복된 데이터 입니다.", HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
        	return new ResponseEntity<String>("등록 오류 입니다.", HttpStatus.BAD_REQUEST);
		}
        
        return new ResponseEntity<Integer>(1, HttpStatus.OK);                
    }
    
    
    //회원 수정
    @PutMapping("/auth/user")
    public ResponseEntity<Integer> updateUser(@RequestBody User user){  
        userService.updateUser(user);    
        //여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
        //하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해 줄 것임.
        
        //서비스에서 트랜잭션 완료후(즉DB 변경후) 컨트롤에서 실행해야 시큐리티 세션이 변경된다.
//        Authentication authentication= new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(authentication);    	    
//    	
        sessionReset(user);
       
    	//버전업으로 다음 처럼 세션을 직접 접근해서 변경은 안된다.
    	//session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);       
    	return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
    
    
    /**
     *  시큐리티 세션 재설정  
     *  시큐리티 세션 재설정은 서비스에서  트랜잭션이 종료된 후 실행되는  컨트롤에서 설정해야 한다. 
     */
    public  void sessionReset(User user) {    	
        //유저 한명에 권한이 여러개 설정될수 있기 때문에 list 한다. ex)GUEST,USER ,MANAGER,ADMIN  
        Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(() -> user.getRole().toString());
       
		UserDetails  userDetails=principalDetailService.loadUserByUsername(user.getUsername());
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    
    
    
    
    
    
    
    
    
    
/*    
    @PostMapping("/api/user/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
    	log.info("UserApiController  : login 호출 됨");
        
        User principal=userService.login(user); //principal (접근주체)
        
        if(principal!=null) {
        	session.setAttribute("principal", principal);
        	return new ResponseEntity<Integer>(1, HttpStatus.OK);
        }else {
        	return new ResponseEntity<Integer>(2, HttpStatus.FOUND);
        }
        
    }
*/
    
    
    
    
}
