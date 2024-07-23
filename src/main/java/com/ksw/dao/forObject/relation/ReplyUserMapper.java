package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ksw.object.entity.Reply;
import com.ksw.object.relation.ReplyUser;

@Mapper
public interface ReplyUserMapper {

	@Select("SELECT "
	        + "r.replyNo, "
	        + "r.createdAt, "
	        + "r.isActive, " 
	        + "r.parentReply, "
	        + "r.replyContent, "
	        + "r.updatedAt, " 
	        + "u.userNo, "
	        + "u.createdAt AS userCreatedAt, "
	        + "u.isActive, " 
	        + "u.nickname, "
	        + "u.type " 
	        + "FROM reply r "
	        + "JOIN noteReply nr ON r.replyNo = nr.replyNo "
	        + "JOIN replyUser ru ON r.replyNo = ru.replyNo "
	        + "JOIN user u ON ru.userNo = u.userNo "
	        + "WHERE nr.noteNo = #{noteNo} AND (r.isActive IS NULL OR r.isActive != 0) "
	        + "ORDER BY CASE WHEN r.updatedAt IS NULL THEN r.createdAt ELSE r.updatedAt END DESC")
	List<Map<String, Object>> getRepliesAndWriterByNoteNo(@Param("noteNo") Integer noteNo);

    
    @Insert("INSERT INTO replyUser (userNo, replyNo) VALUES (#{userNo}, #{replyNo})")
    void insert(@Param("replyNo") Integer replyNo, @Param("userNo") Integer userNo);

    @Select("SELECT * FROM replyUser WHERE replyNo = #{replyNo}")
    ReplyUser findByReplyNo(@Param("replyNo") Integer replyNo);

    @Select("SELECT * FROM replyUser WHERE userNo = #{userNo}")
    List<ReplyUser> findByUserNo(@Param("userNo") Integer userNo);

    @Select("SELECT * FROM replyUser")
    List<ReplyUser> findAll();

    @Update("UPDATE replyUser SET userNo = #{userNo} WHERE replyNo = #{replyNo}")
    void update(ReplyUser replyUser);

    @Delete("DELETE FROM replyUser WHERE replyNo = #{replyNo}")
    void deleteByReplyNo(@Param("replyNo") Integer replyNo);

    @Delete("DELETE FROM replyUser WHERE userNo = #{userNo}")
    void deleteByUserNo(@Param("userNo") Integer userNo);
    
    @Insert("INSERT INTO reply (replyContent, parentReply) VALUES (#{replyContent}, #{replyDTO.parentReply})")
    Reply insertReply(Reply reply);

}
