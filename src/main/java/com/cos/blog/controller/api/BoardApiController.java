package com.cos.blog.controller.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	final private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseEntity<?> save(@RequestBody Board board, Principal principal) {
		try {
			log.info("save :  {} , {} ", principal.getName(), board);
			return new ResponseEntity<Integer>(boardService.boardSave(board, principal), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("등록 처리 오류  입니다.", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseEntity<?> deleteByid(@PathVariable("id") Long id) {
		boardService.deleteByid(id);
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}

	@PutMapping("/api/board/{id}")
	public ResponseEntity<Integer> updateBoard(@PathVariable long id, @RequestBody Board board) {
		boardService.updateBoard(id, board);
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}

	
	//데이터를 받을 때 컨트롤러에서 dto 를 만들어서 받는게 좋다.
	//dto 사용하지 않는 이유는
	// 댓글 등록
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseEntity<?> replySave(@PathVariable(value = "boardId") long boardId, @RequestBody ReplySaveRequestDto replySaveRequestDto,
			@AuthenticationPrincipal PrincipalDetails principal) {
		try {
			replySaveRequestDto.setUserId(principal.getUser().getId());
			boardService.replySave(replySaveRequestDto);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("등록 처리 오류  입니다.", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	//댓글 삭제
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseEntity<?> replyDelete(@PathVariable(value = "boardId") long boardId ,
			@PathVariable(value = "replyId") long replyId, @AuthenticationPrincipal PrincipalDetails principal){		
		int result=boardService.replyDelete(replyId , principal);
		if(result!=1) {
			return new ResponseEntity<String>("삭제 처리 할수 없습니다.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
	
	
	

}
