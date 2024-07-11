package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ksw.object.relation.ReplyUser;

@Mapper
public interface ReplyUserMapper {


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
