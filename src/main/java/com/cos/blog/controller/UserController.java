package com.cos.blog.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import lombok.RequiredArgsConstructor;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**  허용
//그냥주소가 /이면 index.jsp 허용
//static 이하여 있는  /js/** , /css/** ,  /image/**


@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    

    @GetMapping("/auth/loginForm")
    public String loginForm() {        
        return "user/loginForm";
    }

    

    @GetMapping(value = "/auth/loginForm/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
        return "user/loginForm";
    }
    
    
    @GetMapping("/user/updateForm")
    public String updateUserForm(Principal principal,  Model model) {
    	User userInfo =userService.getByUsername(principal.getName());
    	model.addAttribute("userInfo" ,userInfo);    	
    	return "user/updateUserForm";
    }
    
    
    
    
    
    
    
    
}
