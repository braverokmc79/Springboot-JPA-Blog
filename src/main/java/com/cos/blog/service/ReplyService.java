package com.cos.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Reply;
import com.cos.blog.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {
	
	private final ReplyRepository  replyRepository;
	
	public Page<Reply> replyList(Pageable pageable) {			
		return replyRepository.findAll(pageable);
	}
	
	

}
