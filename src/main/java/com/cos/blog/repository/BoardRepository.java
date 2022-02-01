package com.cos.blog.repository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.cos.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Long> , QuerydslPredicateExecutor<Item> , BoardRepositoryCustom{
	 
}

