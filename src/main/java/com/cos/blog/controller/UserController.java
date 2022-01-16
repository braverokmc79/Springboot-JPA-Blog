package com.cos.blog.controller;

import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/user/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

//    @PostMapping(value = "/user")
//    public String join(@ResponseBody User user){
//        userService.userJoin(user);
//    }


    @GetMapping("/user/loginForm")
    public String loginForm() {        
        return "user/loginForm";
    }

}
