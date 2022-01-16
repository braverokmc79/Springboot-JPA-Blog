package com.cos.blog.controller.api;

import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;

	@PostMapping("/api/user")
	public ResponseEntity<?> save(@RequestBody User user) {
		try {
			System.out.println("user: " +user.toString());
			//실제로 DB에 insert 를 하고 아래에서 리턴
			User  result=userService.userJoin(user);			
		} catch (Exception e) {
			System.out.println("error  :" +e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}


	
}
