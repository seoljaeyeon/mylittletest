package com.ksw.dao.forObject.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	
	
}
