package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ksw.object.relation.NoteReply;

@Mapper
public interface NoteReplyMapper {

    @Insert("INSERT INTO noteReply (noteNo, replyNo) VALUES (#{noteNo}, #{replyNo})")
    void insert(@Param("noteNo") Integer noteNo, @Param("replyNo") Integer replyNo);
	
}
