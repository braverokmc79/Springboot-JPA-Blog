package com.cos.blog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	
	@Autowired
	private BoardService boardService;

    @GetMapping(value={"", "/"})
    public String index(Model model,   Principal principal) {	//컨트롤에서 세션을 어떻게 찾는지? 
    	if(principal!=null) {    		
    		System.out.println("로그인 사용자 아이디 : " +principal.getName());
    	}
    	model.addAttribute("boards", boardService.boardList());
    	return "index";
    }


    
    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
    	return "board/saveForm";
    }
    
    
    
}
