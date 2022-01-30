package com.cos.blog.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping(value={"", "/"})
    public String index(Principal principal) {	//컨트롤에서 세션을 어떻게 찾는지? 
    	if(principal!=null) {    		
    		System.out.println("로그인 사용자 아이디 : " +principal.getName());
    	}    	
    	return "index";
    }


    
    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
    	return "board/saveForm";
    }
    
    
    
}
