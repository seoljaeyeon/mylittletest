package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ksw.dto.forObject.relation.ReplyUserDTO;
import com.ksw.object.relation.ReplyUser;

@Mapper
public interface ReplyUserMapper {

	
    @Select("SELECT "
    		+ "r.replyNo AS r_replyNo, "
    		+ "r.createdAt AS r_createdAt, "
    		+ "r.isActive AS r_isActive, " 
    		+ "r.parentReply AS r_parentReply, "
            + "r.replyContent AS r_replyContent, "
            + "r.updatedAt AS r_updatedAt, " 
            + "u.userNo AS u_userNo, "
            + "u.createdAt AS u_createdAt, "
            + "u.isActive AS u_isActive, " 
            + "u.nickname AS u_nickname, "
            + "u.type AS u_type " +
            "FROM reply r " +
            "JOIN user u ON r.userNo = u.userNo " +
            "JOIN noteReply n ON r.replyNo = n.replyNo " +
            "WHERE n.noteNo = #{noteNo} AND r.isActive != 0")
    @Results({
        @Result(property = "replyDTO.replyNo", column = "r_replyNo"),
        @Result(property = "replyDTO.createdAt", column = "r_createdAt"),
        @Result(property = "replyDTO.isActive", column = "r_isActive"),
        @Result(property = "replyDTO.parentReply", column = "r_parentReply"),
        @Result(property = "replyDTO.replyContent", column = "r_replyContent"),
        @Result(property = "replyDTO.updatedAt", column = "r_updatedAt"),
        @Result(property = "userDTO.userNo", column = "u_userNo"),
        @Result(property = "userDTO.createdAt", column = "u_createdAt"),
        @Result(property = "userDTO.isActive", column = "u_isActive"),
        @Result(property = "userDTO.nickname", column = "u_nickname"),
        @Result(property = "userDTO.type", column = "u_type")
    })
	List<ReplyUserDTO> getRepliesAndWriterByNoteno(@Param("noteNo") Integer noteNo);
    
    @Insert("INSERT INTO replyUser (userNo, replyNo) VALUES (#{user.userNo}, #{reply.replyNo})")
    ReplyUser insert(ReplyUser replyUser);

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
	
}
