package com.cos.blog.service;

import java.security.Principal;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.BoardDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.SearchCond;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

/**
 * 서비스
 * 1. 트랜잭션 관리
 * 2. 두개 이상 서비스 create, update,
 *
 * select 만 있을 경우 : @Transactional(readOnly = true)
 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC 를 해준다. *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    
    private final Logger log=LoggerFactory.getLogger(this.getClass());
	
	private final BoardRepository boardRepository;

	final private UserService userService;
        
	//글쓰기
    public int boardSave(Board board , Principal principal) {
    	int result=0;
    	User user= userService.getByUsername(principal.getName());
    	if(user!=null) {
        	log.info("가져온 유저 정보  {} ", user.toString());        	
        	board.setUser(user);
        	board.setCount(0);
        	boardRepository.save(board);
        	result=1;
    	}
    	return result;
    }

    @Transactional(readOnly = true)
	public Page<BoardDto> boardSearchList(SearchCond  searchCond , Pageable pageable) {    	
		return boardRepository.boardSearchList(searchCond ,pageable);
	}

    
    
    //글 상세보기
	public Board boardDetail(Long id) {		
		return boardRepository.findById(id).orElseThrow(EntityExistsException::new);		
	}
    
    

	

}
