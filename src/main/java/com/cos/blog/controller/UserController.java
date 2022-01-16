package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/user/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    

    @GetMapping("/user/loginForm")
    public String loginForm() {        
        return "user/loginForm";
    }

    
}
