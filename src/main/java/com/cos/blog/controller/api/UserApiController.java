package com.cos.blog.controller.api;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final Logger log=LoggerFactory.getLogger(this.getClass());
 			
			
    private final UserService userService;

   
    
    @PostMapping("/auth/joinProc")
    public ResponseEntity<?> save(@RequestBody User user) {
        //실제로 DB에 insert 를 하고 아래에서 리턴
    	log.info("회원 가입");

        try{
        	 userService.userJoin(user);
             return new ResponseEntity<Integer>(1, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<String>("이미등록된 아이디 입니다.", HttpStatus.BAD_REQUEST);
        }
       
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
