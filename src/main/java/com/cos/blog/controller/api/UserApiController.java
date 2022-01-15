package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;

@RestController
public class UserApiController {

	
	@PostMapping("/api/user")
	public ResponseEntity<?> save(@RequestBody User user) {
		try {
			System.out.println("user: " +user.toString());
		} catch (Exception e) {
			System.out.println("error  :" +e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}


	
}
