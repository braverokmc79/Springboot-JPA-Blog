package com.cos.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cos.blog.dto.BoardDto;
import com.cos.blog.model.SearchCond;

public interface BoardRepositoryCustom {
	
	 Page<BoardDto> boardSearchList(SearchCond searchCond, Pageable pageable);
	
}
