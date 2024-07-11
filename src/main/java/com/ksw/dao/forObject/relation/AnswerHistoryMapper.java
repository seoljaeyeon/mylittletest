package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.*;

import com.ksw.object.relation.AnswerHistory;

import java.util.List;

@Mapper
public interface AnswerHistoryMapper {

    @Insert("INSERT INTO answerHistory "
    		+ "(noteNo, answerNo, userNo, createdAt, updatedAt) "
    		+ "VALUES (#{noteNo}, #{answerNo}, #{userNo}, #{createdAt}, #{updatedAt})")
    void insert(AnswerHistory answerHistory);

    @Select("SELECT a.answerType "
    		+ "FROM answerHistory ah "
    		+ "JOIN answer a ON ah.answerNo = a.answerNo "
    		+ "WHERE ah.noteNo = #{noteNo} "
    		+ "AND ah.userNo = #{userNo} "
    		+ "ORDER BY a.createdat DESC LIMIT 1")
    Integer findAnswerByNoteNoAndUserNo(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);

    @Select("SELECT * FROM answerHistory WHERE noteNo = #{noteNo}")
    List<AnswerHistory> findByNoteNo(Integer noteNo);

    @Select("SELECT * FROM answerHistory")
    List<AnswerHistory> findAll();

    @Update("UPDATE answerHistory SET answerNo = #{answerNo}, createdAt = #{createdAt}, updatedAt = #{updatedAt} WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
    void update(AnswerHistory answerHistory);

    @Delete("DELETE FROM answerHistory WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
    void delete(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);
}