package com.ksw.dao.object;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.jpa.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	
	
}
