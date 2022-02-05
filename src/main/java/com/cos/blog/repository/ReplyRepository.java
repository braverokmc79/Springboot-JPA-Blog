package com.cos.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

	@Transactional
	@Modifying
	@Query(value="INSERT INTO `reply` (userId, boardId, content, createDate ) VALUES(:userId, :boardId, :content, now() )", nativeQuery = true)
	int mSave(@Param("userId") long userId, @Param("boardId") long boardId, @Param("content") String content);

	
	
}
