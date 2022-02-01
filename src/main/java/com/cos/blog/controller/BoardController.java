package com.cos.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.dto.BoardDto;
import com.cos.blog.model.SearchCond;
import com.cos.blog.service.BoardService;
import com.cos.blog.util.Pagination;

@Controller
public class BoardController {

	
	@Autowired
	private BoardService boardService;

	//@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    @GetMapping(value={"", "/"})
    public String index(SearchCond  searchCond , 
    			@RequestParam(value = "page",defaultValue ="1", required = false) 
    			Optional<Integer> page,   Model model) {
        //페이징을 위해서 PageRequest.of 메소드를 통해 Pageable 객체를 생성합니다.
        //첫 번째 파라미터로 조회할 페이지 번호, 두 번째 파라미터로 한 번에 가지고 올 데이터 수를 넣어줍니다.
        //URL 경로에 페이지 번호가 있으면 해당 페이지르 조회하도록 세팅하고, 페이지 번호가 없으면 0페이지를 조회하도록 합니다.
    	System.out.println(" 페이지 파리미터 : " +page);
        Pageable pageable= PageRequest.of(page.isPresent()? page.get()-1 :0, 3);
        
    	Page<BoardDto> boards= boardService.boardSearchList(searchCond , pageable);
    	
    	//페이지 블럭 계산을 위한 처리
    	//전체 페이지, 현재 페이지, 한 페이지당 게시글 수, 한 블럭(range)당 페이지 수 
    	Pagination pagination =new Pagination((int)boards.getTotalElements(),  page.get(),  boards.getSize()  , 5);

    	model.addAttribute("boards", boards);
    	model.addAttribute("searchCond", searchCond);
    	model.addAttribute("pagination", pagination);
    	return "index";
    }



    
    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
    	return "board/saveForm";
    }
    
  /*  

 	"content": [
 
 	],
    "pageable": {
        "sort": {
          "sorted": true,
          "unsorted": false,
          "empty": false
        },
        "pageNumber": 0,
        "pageSize": 1,
        "offset": 0,
        "paged": true,
        "unpaged": false
      },
      "last": false,
      "totalElements": 4,
      "totalPages": 4,
      "first": true,
      "numberOfElements": 1,
      "size": 1,
      "number": 0,
      "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
      },
      "empty": false
    
   */
    
    
}
